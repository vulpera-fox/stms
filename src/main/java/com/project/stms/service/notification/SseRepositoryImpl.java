package com.project.stms.service.notification;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Service
public class SseRepositoryImpl implements SseRepository{
	
    private final Map<String, SseEmitter> emitters = new ConcurrentHashMap<>();
    private final Map<String, Object> eventCache = new ConcurrentHashMap<>();
	
	@Override
    public SseEmitter save(String emitterId, SseEmitter sseEmitter) {
        emitters.put(emitterId, sseEmitter);
        return sseEmitter;
    }

    @Override
    public void saveEventCache(String eventCacheId, Object event) {
        eventCache.put(eventCacheId, event);
    }

    
    public Map<String, SseEmitter> findAllStartWithById(String rcv_id) {
        return emitters.entrySet().stream() //여러개의 Emitter가 존재할 수 있기떄문에 stream 사용
                .filter(entry -> entry.getKey().startsWith(rcv_id))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Override
    public Map<String, SseEmitter> findAllEmitterStartWithByEmailInList(List emails) {
        return null;
    }

    @Override
    public Map<String, Object> findAllEventCacheStartWithById(String rcv_id) {
        return emitters.entrySet().stream()
                .filter(entry -> entry.getKey().startsWith(rcv_id))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Override
    public void deleteById(String id) {
        emitters.remove(id);
    }

    @Override
    public void deleteAllEmitterStartWithId(String email) {
        emitters.forEach((key, emitter) -> {
            if (key.startsWith(email)){
                emitters.remove(key);
            }
        });
    }

    @Override
    public void deleteAllEventCacheStartWithId(String email) {
        emitters.forEach((key, emitter) -> {
            if (key.startsWith(email)){
                emitters.remove(key);
            }
        });
    }
	
	
}
