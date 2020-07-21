package com.example.cachinginfinispan;

import org.infinispan.configuration.cache.CacheMode;
import org.infinispan.configuration.cache.Configuration;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.spring.starter.embedded.InfinispanCacheConfigurer;
import org.infinispan.spring.starter.embedded.InfinispanGlobalConfigurationCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
@EnableCaching
public class CachingConfig {
    private final String CLUSTER_NAME = "local-cluster";

    @Bean
    public InfinispanCacheConfigurer cacheConfigurer() {
        return manager -> {
            final Configuration ispnConfig = new ConfigurationBuilder()
                    .clustering()
                    .cacheMode(CacheMode.LOCAL)
                    .build();

            manager.defineConfiguration("books", ispnConfig);
        };
    }

    @Bean
    public InfinispanGlobalConfigurationCustomizer globalCustomizer() {
        return builder -> builder.transport()
                .clusterName(CLUSTER_NAME)
                .globalJmxStatistics().enable();
    }
}
