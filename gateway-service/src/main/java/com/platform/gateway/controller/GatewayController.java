package com.platform.gateway.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@RestController
@RequestMapping("/api")
public class GatewayController {

    private final RestTemplate restTemplate;

    public GatewayController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @RequestMapping("/orchestrator/**")
    public ResponseEntity<String> routeToOrchestrator(HttpServletRequest request) {
        return forward(request, "http://localhost:8081", "/api/orchestrator");
    }

    @RequestMapping("/realtime/**")
    public ResponseEntity<String> routeToRealtime(HttpServletRequest request) {
        return forward(request, "http://localhost:8082", "/api/realtime");
    }

    private ResponseEntity<String> forward(
            HttpServletRequest request,
            String baseUrl,
            String prefix
    ) {
        String path = request.getRequestURI().replace(prefix, "");
        URI target = URI.create(baseUrl + path);

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Client-Id", (String) request.getAttribute("X-Client-Id"));
        headers.add("X-Request-Id", (String) request.getAttribute("X-Request-Id"));

        RequestEntity<Void> outgoing =
                RequestEntity.get(target).headers(headers).build();

        return restTemplate.exchange(outgoing, String.class);
    }
}
