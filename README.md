# Orion Platform

A distributed backend platform featuring:
- API Gateway
- Workflow Orchestrator
- Background Workers
- Realtime Collaboration Service

## Services
- gateway-service: API Gateway (entry point)
- orchestrator-service: Workflow orchestration (planned)
- worker-service: Background task execution (planned)
- realtime-service: WebSocket-based collaboration (planned)

## Infrastructure
- Redis: rate limiting, presence, caching
- Postgres: workflows, documents, durable state

## High-level Flow
Client → API Gateway → Backend Services
