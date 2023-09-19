package com.project.stms.service.notification;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Service
public class SseRepositoryImpl implements SseRepository {
	
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

    @Override
    public Map<String, SseEmitter> findAllEmitterStartWithById(String user_id) {
        return emitters.entrySet().stream() //여러개의 Emitter가 존재할 수 있기떄문에 stream 사용
                .filter(entry -> entry.getKey().startsWith(user_id))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Override
    public Map<String, SseEmitter> findAllEmitterStartWithByEmailInList(List emails) {
        return null;
    }

    @Override
    public Map<String, Object> findAllEventCacheStartWithById(String user_id) {
        return emitters.entrySet().stream()
                .filter(entry -> entry.getKey().startsWith(user_id))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Override
    public void deleteById(String id) {
        emitters.remove(id);
    }

    @Override
    public void deleteAllEmitterStartWithId(String user_id) {
        emitters.forEach((key, emitter) -> {
            if (key.startsWith(user_id)){
                emitters.remove(key);
            }
        });
    }

    @Override
    public void deleteAllEventCacheStartWithId(String user_id) {
        emitters.forEach((key, emitter) -> {
            if (key.startsWith(user_id)){
                emitters.remove(key);
            }
        });
    }
}
