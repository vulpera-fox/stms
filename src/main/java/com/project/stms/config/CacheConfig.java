package com.project.stms.config;

import java.util.concurrent.TimeUnit;

import org.infinispan.Cache;
import org.infinispan.commons.marshall.ProtoStreamMarshaller;
import org.infinispan.configuration.cache.CacheMode;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.configuration.cache.TransactionConfigurationBuilder;
import org.infinispan.configuration.global.GlobalConfigurationBuilder;
import org.infinispan.configuration.global.GlobalConfigurationChildBuilder;
import org.infinispan.configuration.parsing.ConfigurationBuilderHolder;
import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.manager.EmbeddedCacheManager;
import org.infinispan.transaction.LockingMode;
import org.infinispan.transaction.TransactionMode;
import org.infinispan.util.concurrent.IsolationLevel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public class CacheConfig {

	private final Environment environment;
	
	public CacheConfig(Environment environment) {
		this.environment = environment;
	}
	
	@Bean("chcheManager")
	public EmbeddedCacheManager cacheManager() {
		GlobalConfigurationBuilder global = new GlobalConfigurationBuilder()
													.transport()
													.defaultTransport()
													.clusterName("foobar-api-" + (environment.getProperty("SPRING_PROFILES_ACTIVE") != null ? environment.getProperty("SPRING_PROFILES_ACTIVE") : "local"))
													.defaultCacheName("default-cache");
		
		global.serialization().marshaller(new ProtoStreamMarshaller());
		
		return new DefaultCacheManager(((GlobalConfigurationChildBuilder) new ConfigurationBuilderHolder(Thread.currentThread().getContextClassLoader(), global)).build(), true);
	}
	
	@Bean("sseEmitterCache")
    public Cache<String, SseEmitter> sseEmitterCache(@Qualifier("cacheManager") EmbeddedCacheManager cacheManager) {
        TransactionConfigurationBuilder config = new ConfigurationBuilder().expiration().lifespan(10, TimeUnit.MINUTES)
            .clustering().cacheMode(CacheMode.LOCAL)
            .locking().isolationLevel(IsolationLevel.READ_COMMITTED)
            .useLockStriping(false)
            .lockAcquisitionTimeout(10, TimeUnit.SECONDS)
            .transaction().lockingMode(LockingMode.OPTIMISTIC)
            .transactionMode(TransactionMode.NON_TRANSACTIONAL);

        cacheManager.defineConfiguration("sse-emitter-cache", config.build());

        return cacheManager.getCache("sse-emitter-cache");
    }

    @Bean("sseEventCache")
    public Cache<String, String> sseEventCache(@Qualifier("cacheManager") EmbeddedCacheManager cacheManager) {
        TransactionConfigurationBuilder config = new ConfigurationBuilder().expiration().lifespan(1, TimeUnit.MINUTES)
            .clustering().cacheMode(CacheMode.REPL_ASYNC)
            .locking().isolationLevel(IsolationLevel.READ_COMMITTED)
            .useLockStriping(false)
            .lockAcquisitionTimeout(10, TimeUnit.SECONDS)
            .transaction().lockingMode(LockingMode.OPTIMISTIC)
            .transactionMode(TransactionMode.NON_TRANSACTIONAL);

        cacheManager.defineConfiguration("sse-event-cache", config.build());

        return cacheManager.getCache("sse-event-cache");
    }
	
}
