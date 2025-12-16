package com.platform.gateway.controller;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class GatewayController {

    private final RestTemplate restTemplate;

    public GatewayController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @RequestMapping("/orchestrator/**")
    public ResponseEntity<String> routeToOrchestrator(HttpServletRequest request) {
        String targetUrl = buildTargetUrl(
                request,
                "http://localhost:8081",
                "/api/orchestrator"
        );
        return restTemplate.exchange(targetUrl, HttpMethod.GET, null, String.class);
    }

    @RequestMapping("/realtime/**")
    public ResponseEntity<String> routeToRealtime(HttpServletRequest request) {
        String targetUrl = buildTargetUrl(
                request,
                "http://localhost:8082",
                "/api/realtime"
        );
        return restTemplate.exchange(targetUrl, HttpMethod.GET, null, String.class);
    }

    private String buildTargetUrl(HttpServletRequest request, String baseUrl, String prefix) {
        String path = request.getRequestURI().replace(prefix, "");
        return baseUrl + path;
    }
}
