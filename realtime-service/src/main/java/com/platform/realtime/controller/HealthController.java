package com.platform.realtime.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HealthController {
    @GetMapping("/health")
    public String health(
            @RequestHeader(value = "X-Client-Id", required = false) String clientId,
            @RequestHeader(value = "X-Request-Id", required = false) String requestId
    ) {
        return "Health OK | client=" + clientId + " | reqId=" + requestId;
    }
}
