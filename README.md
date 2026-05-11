# StreamNet

A production-grade real-time social platform built with Spring Boot microservices and React.

## Architecture
Microservice-based backend with service discovery, API gateway, and event-driven communication.

## Tech Stack
**Backend:** Java 17, Spring Boot, Spring Cloud, Spring Security, JWT, PostgreSQL, Kafka, Redis, AWS S3  
**Frontend:** TypeScript, React.js, Redux-Saga, Material-UI, WebSocket

## Features
- Real-time feeds, likes, reposts, replies and quote posts
- WebSocket-powered instant messaging and live notifications
- Scheduled posts and poll creation
- Image uploads via AWS S3
- Full-text search across users and posts
- JWT authentication with email verification

## Microservices
- API Gateway
- User Service
- Post Service
- Notification Service
- Chat Service
- Media Service
- Config Server
- Discovery Server

## Getting Started
Requires Docker. Run `docker-compose up --build` to start all services.

## License
MIT
