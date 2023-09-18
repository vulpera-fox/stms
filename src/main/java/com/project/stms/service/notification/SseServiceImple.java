package com.project.stms.service.notification;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.project.stms.command.NotificationVO;

@Service
public class SseServiceImple {

	
	@Autowired
	private SseRepository sseRepository;
	
	private static final Long DEFAULT_TIMEOUT = 60L * 1000 * 60;

    public SseServiceImple(SseRepository sseRepository) {
        this.sseRepository = sseRepository;
    }

    public SseEmitter subscribe(String rcv_id, String lastEventId) {
        // 1
        String id = rcv_id + "_" + System.currentTimeMillis();
        
        // 2
        SseEmitter emitter = sseRepository.save(id, new SseEmitter(DEFAULT_TIMEOUT));

        emitter.onCompletion(() -> sseRepository.deleteById(id));
        emitter.onTimeout(() -> sseRepository.deleteById(id));

	// 3
        // 503 에러를 방지하기 위한 더미 이벤트 전송
        sendToClient(emitter, id, "{rcv_id:" + rcv_id+ "}");

	// 4
        // 클라이언트가 미수신한 Event 목록이 존재할 경우 전송하여 Event 유실을 예방
        if (!lastEventId.isEmpty()) {
            Map<String, Object> events = sseRepository.findAllEventCacheStartWithById(String.valueOf(rcv_id));
            events.entrySet().stream()
                  .filter(entry -> lastEventId.compareTo(entry.getKey()) < 0)
                  .forEach(entry -> sendToClient(emitter, entry.getKey(), entry.getValue()));
        }

        return emitter;
    }

    // 3
    private void sendToClient(SseEmitter emitter, String rcv_id, Object data) {
        try {
            emitter.send(SseEmitter.event()
                                   .id(rcv_id)
                                   .name("sse")
                                   .data(data));
        } catch (IOException exception) {
        	sseRepository.deleteById(rcv_id);
            throw new RuntimeException("연결 오류!");
        }
    }
    
    public void send(String rcv_id, String noti_nm) {
        NotificationVO vo = createNotification(rcv_id, noti_nm);
        String id = String.valueOf(vo.getRcv_id());
        
        // 로그인 한 유저의 SseEmitter 모두 가져오기
        Map<String, SseEmitter> sseEmitters = sseRepository.findAllStartWithById(id);
        sseEmitters.forEach(
                (key, emitter) -> {
                    // 데이터 캐시 저장(유실된 데이터 처리하기 위함)
                	sseRepository.saveEventCache(key, vo);
                    // 데이터 전송
                    sendToClient(emitter, key, vo);
                }
        );
    }

    private NotificationVO createNotification(String rcv_id, String noti_nm) {
        return NotificationVO.builder()
                           .rcv_id(rcv_id)
                           .noti_nm(noti_nm)
                           .noti_dtl(noti_nm + "요청이 생성되었습니다.")
                           .build();
    }
    

}
