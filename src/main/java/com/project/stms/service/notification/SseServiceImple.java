package com.project.stms.service.notification;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.project.stms.command.NotificationVO;

@Service
public class SseServiceImple implements SseService{

	@Autowired
    private SseRepositoryImpl emitterRepository;

    public SseEmitter subscribe(String user_id, String lastEventId) {

        String emitterId = makeTimeIncludeId(user_id);

        SseEmitter emitter;

      //글쓴이가 버그 방지용으로 만든 코드입니다.
        if (emitterRepository.findAllEmitterStartWithById(user_id) != null){
            emitterRepository.deleteAllEmitterStartWithId(user_id);
            emitter = emitterRepository.save(emitterId, new SseEmitter(Long.MAX_VALUE)); //id가 key, SseEmitter가 value
        }
        else {
            emitter = emitterRepository.save(emitterId, new SseEmitter(Long.MAX_VALUE)); //id가 key, SseEmitter가 value
        }

       //오류 종류별 구독 취소 처리
        emitter.onCompletion(() -> emitterRepository.deleteById(emitterId)); //네트워크 오류
        emitter.onTimeout(() -> emitterRepository.deleteById(emitterId)); //시간 초과
        emitter.onError((e) -> emitterRepository.deleteById(emitterId)); //오류

        // 503 에러를 방지하기 위한 더미 이벤트 전송
        String eventId = makeTimeIncludeId(user_id);
        sendNotification(emitter, eventId, emitterId, "EventStream Created. [userId=" + user_id + "]");

        // 클라이언트가 미수신한 Event 목록이 존재할 경우 전송하여 Event 유실을 예방
        if (hasLostData(lastEventId)) {
            sendLostData(lastEventId, user_id, emitterId, emitter);
        }

        return emitter;
    }
    
  //단순 알림 전송
    private void sendNotification(SseEmitter emitter, String eventId, String emitterId, Object data) {

        try {
            emitter.send(SseEmitter.event()
                    .id(eventId)
                    .name("sse")
                    .data(data, MediaType.APPLICATION_JSON));
            System.out.println(data + "머 머라고");
        } catch (IOException exception) {
            emitterRepository.deleteById(emitterId);
            emitter.completeWithError(exception);
        }
    }

    private String makeTimeIncludeId(String user_id) { 
    	return user_id + "_" + System.currentTimeMillis(); 
    }//Last-Event-ID의 값을 이용하여 유실된 데이터를 찾는데 필요한 시점을 파악하기 위한 형태
  
  //Last-Event-Id의 존재 여부 boolean 값
    private boolean hasLostData(String lastEventId) {
        return !lastEventId.isEmpty();
    }
    
  //유실된 데이터 다시 전송
    private void sendLostData(String lastEventId, String user_id, String emitterId, SseEmitter emitter) {

        Map<String, Object> eventCaches = emitterRepository.findAllEventCacheStartWithById(user_id);
        eventCaches.entrySet().stream()
                .filter(entry -> lastEventId.compareTo(entry.getKey()) < 0)
                .forEach(entry -> sendNotification(emitter, entry.getKey(), emitterId, entry.getValue()));
    }
    
//    sse연결 요청 응답
/*-----------------------------------------------------------------------------------------------------------------------------------*/
//    서버에서 클라이언트로 일방적인 데이터 보내기

  //1ㄷ1로 특정 유저에게 알림 전송
    public void send(String receiver, String content) {

        NotificationVO notification = createNotification(receiver, content);

        // 로그인 한 유저의 SseEmitter 모두 가져오기
        Map<String, SseEmitter> sseEmitters = emitterRepository.findAllEmitterStartWithById(receiver);

        sseEmitters.forEach(
                (key, emitter) -> {
                    // 데이터 캐시 저장(유실된 데이터 처리하기 위함)
                    emitterRepository.saveEventCache(key, notification);
                    // 데이터 전송
                    sendToClient(emitter, key, notification);
                }
        );
    }
  //1ㄷ1로 List에 존재하는 특정 유저에게 알림 전송
    public void sendList(List receiverList, String content, String type, String urlValue) {

        List<NotificationVO> notifications = new ArrayList<>();

        Map<String, SseEmitter> sseEmitters;

        for (int i = 0; i < receiverList.size(); i++) {

            int finalI = i;

            sseEmitters = new HashMap<>();

            notifications.add(createNotification(receiverList.get(i).toString(), content));

            sseEmitters.putAll(emitterRepository.findAllEmitterStartWithById(receiverList.get(i).toString()));

            sseEmitters.forEach(
                    (key, emitter) -> {
                        // 데이터 캐시 저장(유실된 데이터 처리하기 위함)
                        emitterRepository.saveEventCache(key, notifications.get(finalI));
                        // 데이터 전송
                        sendToClient(emitter, key, notifications.get(finalI));
                    }
            );
        }
    }

  //타입별 알림 생성
    public NotificationVO createNotification(String receiver, String content) {

            return NotificationVO.builder()
                    .rcv_id("a에게")
                    .noti_nm("bb라는 제목으으로")
                    .noti_dtl("cc를 보냄!")
                    .build();
    }

  //알림 전송
    public void sendToClient(SseEmitter emitter, String id, Object data) {

        try {
            emitter.send(SseEmitter.event()
                    .id(id)
                    .name("sse")
                    .data(data, MediaType.APPLICATION_JSON)
                    .reconnectTime(0));

            emitter.complete();

            emitterRepository.deleteById(id);

        } catch (Exception exception) {
            emitterRepository.deleteById(id);
            emitter.completeWithError(exception);
        }
    }
}