# Fault‑Tolerant Microservice Playground

Welcome to my **hands‑on microservices lab**—a place where Spring Boot services roam free, Resilience4j keeps them safe, Eureka shows them the way home, and Docker + Kubernetes do the heavy lifting. This repo captures everything I’ve been tinkering with while learning to build, containerise, and harden cloud‑native apps.

---

## 📚 What you’ll find

| Layer             | Tech                                                          | Why it’s here                                           |
| ----------------- | ------------------------------------------------------------- | ------------------------------------------------------- |
| **Service**       | Spring Boot 3                                                 | Fast, opinionated Java foundation                       |
| **Resilience**    | Resilience4j (Circuit Breaker, Retry, Rate Limiter, Bulkhead) | Protect each service from failure and overload          |
| **Discovery**     | Eureka Server + OpenFeign                                     | Dynamic service lookup & type‑safe REST clients         |
| **Gateway**       | Spring Cloud Gateway                                          | Single entry point, path routing, filters & rate limits |
| **Config**        | Spring Cloud Config Server                                    | Centralised, version‑controlled configuration           |
| **Docs**          | Springdoc‑OpenAPI (Swagger UI)                                | Live API documentation per service                      |
| **Containers**    | Docker & Docker Compose                                       | Repeatable local runs                                   |
| **Orchestration** | Kubernetes manifests                                          | Production‑ready deployment                             |

---

## 🏗️ Architecture at a glance

```
┌──────────────┐     ┌──────────────┐
│  React App   │──▶ │  Gateway      │──┐
└──────────────┘     └──────────────┘  │  (routes, rate‑limits)
                                        │
                                        ▼
     ┌───────────┬─────────────────────────────────────┐
     │ Eureka    │  Accounts │ Loans │ Cards │ …       │
     │ Registry  │<──────────┴─────────────────────────┘
     └───────────┘
```

*Clients hit the **Gateway**, which discovers downstream services through **Eureka**. Each service carries Resilience4j guards that trip before trouble spreads.*

---

## 🚀 Quick start (Docker Compose)

```bash
# build all jars
./mvnw clean package -DskipTests

# spin everything up in detached mode
docker compose up -d --build

# tear everything down
docker compose down
```

Services become available at:

| Service               | URL                                                                            |
| --------------------- | ------------------------------------------------------------------------------ |
| Gateway               | [http://localhost:8080](http://localhost:8080)                                 |
| Eureka UI             | [http://localhost:8761](http://localhost:8761)                                 |
| Config Server         | [http://localhost:8071/actuator/health](http://localhost:8071/actuator/health) |
| Swagger UI (Accounts) | [http://localhost:9000/swagger-ui.html](http://localhost:9000/swagger-ui.html) |

---

## 🔄 Resilience patterns implemented

| Pattern         | Where                                        | Deep‑dive article                                                                                                                                                                                                                                                                                             |
| --------------- | -------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Circuit Breaker | `@CircuitBreaker(name="accountsCB")`         | \[Medium] Circuit Breaker in Spring Boot → [https://medium.com/@araviku04/circuit-breaker-pattern-in-spring-boot-with-resilience4j-and-spring-cloud-gateway-5b4a0d2c161f](https://medium.com/@araviku04/circuit-breaker-pattern-in-spring-boot-with-resilience4j-and-spring-cloud-gateway-5b4a0d2c161f)       |
| Rate Limiter    | `@RateLimiter(name="gatewayRL")`             | \[Medium] Rate Limiter in Spring Cloud Gateway → [https://medium.com/@araviku04/rate-limiter-pattern-in-microservices-using-spring-cloud-gateway-and-resilience4j-478373e68d44](https://medium.com/@araviku04/rate-limiter-pattern-in-microservices-using-spring-cloud-gateway-and-resilience4j-478373e68d44) |
| Retry           | `@Retry(name="loansRetry")`                  | \[Medium] Retry Pattern → [https://medium.com/@araviku04/retry-pattern-in-microservices-with-spring-boot-and-resilience4j-bbb9fbd47438](https://medium.com/@araviku04/retry-pattern-in-microservices-with-spring-boot-and-resilience4j-bbb9fbd47438)                                                          |
| Bulkhead        | `@Bulkhead(name="cardsBH", type=THREADPOOL)` | \[Medium] Bulkhead Pattern → [https://medium.com/@araviku04/bulkhead-pattern-in-microservices-with-spring-boot-resilience4j-7285e6e88a6f](https://medium.com/@araviku04/bulkhead-pattern-in-microservices-with-spring-boot-resilience4j-7285e6e88a6f)                                                         |

Each pattern is wired with **Spring Boot actuator** metrics; hit `/actuator/metrics/resilience4j.*` to watch them in real time.

---

## 🛰️ Service discovery & Feign clients

```java
@FeignClient(name = "loans")
public interface LoansClient {
  @GetMapping("/loans/{customerId}")
  List<LoanDTO> fetchLoans(@PathVariable String customerId);
}
```

The `name` attribute matches the service‑ID registered in **Eureka**, giving us load‑balanced calls with zero hard‑coded URLs.

---

## ⚙️ Centralised configuration

* **Spring Cloud Config Server** backed by a Git repo (`config-repo/`) holds properties per environment.
* Services bootstrap with `spring.config.import=configserver:` so new values can be hot‑reloaded via `/actuator/refresh`.

---

## 📖 OpenAPI docs

Each service ships with **springdoc‑openapi**:

```
GET /v3/api-docs       # raw JSON
GET /swagger-ui.html   # interactive UI
```

Combine this with the Gateway’s aggregation to expose a single consolidated API portal.

---

## 🐳 Docker cheat‑sheet

Need a quick reminder? Here are the commands I reach for most (IDs are trimmed for brevity):

|  #  | Command                                   | What it does                                      |
| :-: | ----------------------------------------- | ------------------------------------------------- |
|  1  | `docker container logs -f <cid>`          | Tail container logs live                          |
|  2  | `docker rm <cid>`                         | Delete one or more stopped containers             |
|  3  | `docker container prune`                  | Remove **all** stopped containers                 |
|  4  | `docker image push <registry>/<user>:tag` | Push image to registry                            |
|  5  | `docker image pull <registry>/<user>:tag` | Pull image from registry                          |
|  6  | `docker image prune`                      | Delete dangling/unused images                     |
|  7  | `docker container stats`                  | Live CPU/RAM/IO per container                     |
|  8  | `docker system prune`                     | Nuke stopped containers, networks, images & cache |
|  9  | `docker rmi <img-id>`                     | Remove one or more images                         |
|  10 | `docker login -u <user>`                  | Log in to Docker Hub                              |
|  11 | `docker logout`                           | Log out of Docker Hub                             |
|  12 | `docker history <img>`                    | Show image layer history                          |
|  13 | `docker exec -it <cid> sh`                | Open interactive shell inside running container   |
|  14 | `docker compose up`                       | Create & start services from *compose* file       |
|  15 | `docker compose down`                     | Stop & remove services defined in compose         |


