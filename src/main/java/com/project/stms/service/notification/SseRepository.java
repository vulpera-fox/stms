package com.project.stms.service.notification;

import java.util.List;
import java.util.Map;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface SseRepository {
	
	SseEmitter save(String emitterId, SseEmitter sseEmitter); //Emitter 저장
    
    void saveEventCache(String eventCacheId, Object event); //이벤트 저장
    
    Map<String, SseEmitter> findAllEmitterStartWithById(String user_id); //해당 회원과  관련된 모든 Emitter를 찾는다
    
    Map<String, SseEmitter> findAllEmitterStartWithByEmailInList(List user_id); //List 에서 해당 회원과  관련된 모든 Emitter를 찾는다(미 개발)
    
    Map<String, Object> findAllEventCacheStartWithById(String user_id); //해당 회원과관련된 모든 이벤트를 찾는다
    
    void deleteById(String id); //Emitter를 지운다
    
    void deleteAllEmitterStartWithId(String user_id); //해당 회원과 관련된 모든 Emitter를 지운다
    
    void deleteAllEventCacheStartWithId(String user_id); //해당 회원과 관련된 모든 이벤트를 지운다
}
