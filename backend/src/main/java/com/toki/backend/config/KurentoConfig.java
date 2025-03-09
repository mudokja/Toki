package com.toki.backend.config;

import org.kurento.client.KurentoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KurentoConfig {
    @Value("${TOKI-KMS-URI}")
    private String TOKI_KMS_URI;
    @Bean
    public KurentoClient kurentoClient() {
        return KurentoClient.create(TOKI_KMS_URI);
    }
}
