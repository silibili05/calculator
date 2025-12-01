# 🧮 Taschenrechner – Microservice Architektur (Java + React/TypeScript)

Dieses Projekt ist ein vollständig modularer **Microservice-basierter Taschenrechner**, bestehend aus mehreren Java-Spring-Boot-Services sowie einem modernen **React/Vite TypeScript-Frontend**.

Die Architektur ist so aufgebaut, wie sie auch in echten professionellen Cloud-Deployments (AWS, Azure, Kubernetes, Docker-Compose) eingesetzt wird.  
Jeder Service ist vollständig **eigenständig, isoliert, unabhängig deploybar**.

---

## 🚀 Gesamtüberblick

Das Projekt besteht aus **6 unabhängigen Services**:

| Service               | Port | Aufgabe |
|----------------------|------|---------|
| **gateway-service**      | 8085 | API-Gateway, zentrale Routing-Schicht |
| **basic-service**        | 8081 | Grundrechenarten (+, −, ×, ÷) |
| **scientific-service**   | 8082 | Potenz, Wurzel, Fakultät, Primzahltest |
| **statistics-service**   | 8083 | Mittelwert, Median, Standardabweichung |
| **history-service**      | 8084 | In-Memory Verlaufsspeicher |
| **frontend-service**     | 8080 | React/Vite User Interface |

---

# 🧩 Architekturübersicht

```
              ┌──────────────────────────┐
              │       Frontend           │ (React + TS, Port 8080)
              └───────────────▲──────────┘
                              │
                              ▼
                   ┌───────────────────────┐
                   │    Gateway-Service     │ (8085)
                   └───────┬───────┬───────┘
                           │       │
         ┌─────────────────┘       └──────────────────┐
         ▼                                             ▼
    ┌──────────────┐                           ┌────────────────┐
    │ Basic Service │                           │ Scientific    │
    │     8081      │                           │     8082      │
    └──────────────┘                           └────────────────┘

         ┌────────────────┐               ┌────────────────────┐
         │ Statistics      │               │   History Service   │
         │      8083       │               │         8084        │
         └────────────────┘               └────────────────────┘
```

---

# 🛠️ Technologien

## Backend (Java)
- Java 17
- Spring Boot 3
- REST API
- Maven Multi-Module
- Docker Container pro Service

## Frontend (React)
- React 18
- TypeScript
- Vite
- Fetch API
- Komponentenbasiertes UI

---

# 🔧 Lokale Installation

### 1. Repository klonen
```bash
git clone <dein-repo>
cd calculator-microservices
```

### 2. Backend bauen
```bash
mvn clean install
```

### 3. Frontend starten
```bash
cd frontend-service
npm install
npm run dev
```

Frontend erreichbar unter:  
👉 **http://localhost:8080**

---

# 🐳 Docker Deployment

Es gibt **ein Dockerfile pro Service**.  
Die benötigten Dockerfiles sind:

| Service | Datei |
|---------|--------|
| basic-service | `basic-service/Dockerfile` |
| scientific-service | `scientific-service/Dockerfile` |
| statistics-service | `statistics-service/Dockerfile` |
| history-service | `history-service/Dockerfile` |
| gateway-service | `gateway-service/Dockerfile` |
| frontend-service | `frontend-service/Dockerfile` |

Beispiel eines Java‑Service‑Dockerfiles:

```dockerfile
FROM eclipse-temurin:17-jdk-alpine
ARG JAR_FILE=target/basic-service-*.jar
WORKDIR /app
COPY ${JAR_FILE} app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
```

Frontend Dockerfile:

```dockerfile
FROM node:18-alpine AS build
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build

FROM nginx:alpine
COPY --from=build /app/dist /usr/share/nginx/html
EXPOSE 8080
CMD ["nginx", "-g", "daemon off;"]
```

---

# 🐙 Docker-Compose – alle Services gleichzeitig starten

```yaml
version: "3.9"

services:

  gateway-service:
    image: gateway-service:latest
    build: ./gateway-service
    ports:
      - "8085:8085"
    depends_on:
      - basic-service
      - scientific-service
      - statistics-service
      - history-service

  basic-service:
    image: basic-service:latest
    build: ./basic-service
    ports:
      - "8081:8081"

  scientific-service:
    image: scientific-service:latest
    build: ./scientific-service
    ports:
      - "8082:8082"

  statistics-service:
    image: statistics-service:latest
    build: ./statistics-service
    ports:
      - "8083:8083"

  history-service:
    image: history-service:latest
    build: ./history-service
    ports:
      - "8084:8084"

  frontend-service:
    image: frontend-service:latest
    build: ./frontend-service
    ports:
      - "8080:80"
```

Starten:
```bash
docker compose up --build
```

---

# 🎨 Frontend – Erweiterungsmöglichkeiten

- Modernes UI‑Framework (Tailwind, Material UI, shadcn/ui)
- Diagramme für Statistik‑Ergebnisse
- Verlauf als Live‑Liste (WebSockets)
- Eingabe‑Validierung
- Dark Mode / Theme Switcher
- Mehrsprachigkeit
- Mobile First Redesign

---

# 🔮 Zukunftsausbau / Empfehlungen

## Backend
- Persistente History (PostgreSQL / MongoDB)
- Authentifizierung via OAuth2 / Keycloak
- Redis Cache
- Skalierung über Kubernetes / AWS ECS

## DevOps
- CI/CD Pipeline (GitHub Actions)
- Deployment in AWS ECR + ECS
- Cloud‑Monitoring
- Helm Charts für Kubernetes

---

# 📜 Fazit

Dieses Projekt zeigt eine moderne Microservice‑Architektur, perfekt geeignet als Lern‑ oder Portfolio‑Projekt – vollständig Cloud‑ready, modular und leicht erweiterbar.
