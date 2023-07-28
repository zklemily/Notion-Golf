package com.zkl.courseinfoservice.golfdata.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "golf")
public class GolfDataConfig {
    private String apiUrl;
    private String authToken;

    public String getApiUrl() {
        return apiUrl;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}
