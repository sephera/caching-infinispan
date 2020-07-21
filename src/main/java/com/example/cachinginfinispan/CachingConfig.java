package com.example.cachinginfinispan;

import org.infinispan.commons.configuration.ClassWhiteList;
import org.infinispan.commons.marshall.JavaSerializationMarshaller;
import org.infinispan.configuration.cache.CacheMode;
import org.infinispan.configuration.cache.Configuration;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.spring.starter.embedded.InfinispanCacheConfigurer;
import org.infinispan.spring.starter.embedded.InfinispanGlobalConfigurationCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

import java.util.Collections;

@org.springframework.context.annotation.Configuration
@EnableCaching
public class CachingConfig {
    private final String CLUSTER_NAME = "local-cluster";

    @Bean
    public InfinispanCacheConfigurer cacheConfigurer() {
        return manager -> {
            final Configuration ispnConfig = new ConfigurationBuilder()
                    .clustering()
                    .cacheMode(CacheMode.DIST_SYNC)
                    .build();

            manager.defineConfiguration("books", ispnConfig);
        };
    }

    @Bean
    public InfinispanGlobalConfigurationCustomizer globalCustomizer() {
        return builder -> builder.transport()
                .clusterName(CLUSTER_NAME)
                .defaultTransport()
                .serialization().marshaller(new JavaSerializationMarshaller(new ClassWhiteList(Collections.singletonList("com.*"))))
                .globalJmxStatistics().enable();
    }
}
