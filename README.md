# 🧮 Taschenrechner -- Microservice Architektur (Java + React/TypeScript)

Dieses Projekt ist ein vollständig modularer **Microservice-basierter
Taschenrechner**, bestehend aus mehreren Java-Spring-Boot-Services sowie
einem modernen **React/Vite TypeScript Frontend**.\
Es wurde entworfen, um eine saubere, skalierbare und leicht erweiterbare
Architektur zu demonstrieren, wie sie in professionellen
Cloud‑Deployments verwendet wird.

------------------------------------------------------------------------

## 🚀 Gesamtüberblick

Das Projekt besteht aus **6 eigenständigen Services**, die alle über
REST kommunizieren:

  -----------------------------------------------------------------------
Service                  Port               Aufgabe
  ------------------------ ------------------ ---------------------------
**gateway-service**      8085               API‑Gateway, zentrale
Routing-Schicht

**basic-service**        8081               Grundrechenarten (+, −, ×,
÷)

**scientific-service**   8082               Potenz, Wurzel, Fakultät,
Primzahltest

**statistics-service**   8083               Mittelwert, Median,
Standardabweichung

**history-service**      8084               In-Memory Speicher für den
Verlauf

**frontend-service**     8080               TypeScript + React UI
-----------------------------------------------------------------------

Die Services können **unabhängig voneinander gestartet, gebaut und
deployed** werden.

------------------------------------------------------------------------

# 🧩 Architektur

              ┌──────────────────────────┐
              │       Frontend           │ (React + TS)
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
    │     8081      │                           │   8082        │
    └──────────────┘                           └────────────────┘

         ┌────────────────┐               ┌────────────────────┐
         │ Statistics      │               │   History Service   │
         │      8083       │               │         8084        │
         └────────────────┘               └────────────────────┘

------------------------------------------------------------------------

# 🛠️ Technologien

## Backend

-   Java 17\
-   Spring Boot 3\
-   Maven Multi‑Module\
-   REST API\
-   Docker Container pro Service

## Frontend

-   React 18\
-   TypeScript\
-   Vite\
-   Fetch API\
-   Komponentenbasierte Architektur

------------------------------------------------------------------------

# 🔧 Lokale Installation

### 1. Repository klonen

``` bash
git clone <dein-repo>
cd calculator-microservices
```

### 2. Services bauen

``` bash
mvn clean install
```

### 3. Alle Microservices starten

Jeder Service läuft in eigenem Terminal:

``` bash
cd basic-service && mvn spring-boot:run
cd scientific-service && mvn spring-boot:run
cd statistics-service && mvn spring-boot:run
cd history-service && mvn spring-boot:run
cd gateway-service && mvn spring-boot:run
```

### 4. Frontend starten (Vite)

``` bash
cd frontend-service
npm install
npm run dev
```

Jetzt erreichbar unter:\
👉 **http://localhost:8080**

------------------------------------------------------------------------

# 🐳 Docker Deployment

Jeder Service enthält ein eigenes Dockerfile.\
Beispiel:

``` bash
cd basic-service
mvn clean package
docker build -t basic-service .
docker run -p 8081:8081 basic-service
```

Frontend:

``` bash
cd frontend-service
docker build -t frontend-service .
docker run -p 8080:8080 frontend-service
```

Optional: Ich kann dir ein fertiges **docker-compose.yaml** erstellen.

------------------------------------------------------------------------

# 🎨 Frontend -- aktueller Stand & Verbesserungsmöglichkeiten

Das Frontend funktioniert und deckt alle wichtigen Berechnungen ab, ist
aber bewusst **minimalistisch gehalten**, damit es leicht erweiterbar
bleibt.

## Mögliche Erweiterungen:

-   Modernes UI‑Framework (Tailwind, Material UI, shadcn/ui)
-   Diagramme (für Statistik-Service)
-   Verlauf als Live‑Liste (WebSockets)
-   Validierung + Fehlermeldungen
-   Dark Mode / Theme Switcher
-   Mehrsprachigkeit
-   Mobile Responsive Layout
-   Drag‑&‑Drop Formel-Builder

------------------------------------------------------------------------

# 🔮 Zukunftsausbau / Empfehlungen

### Backend

-   Austausch der In-Memory History durch PostgreSQL oder MongoDB
-   Authentifizierung über Keycloak oder OAuth2
-   Caching für schwere Berechnungen (Redis)
-   Scaling über Kubernetes / AWS ECS

### DevOps

-   CI/CD Pipeline (GitHub Actions)
-   Docker‑Compose Setup
-   Helm Chart für Kubernetes Deployment

------------------------------------------------------------------------

# 📜 Fazit

Dieses Projekt zeigt eine moderne, saubere Microservice-Architektur mit
klarer Frontend‑Backend‑Trennung.\
Es eignet sich perfekt als Lernprojekt oder Grundlage für echte
Cloud‑Deployments.

Das Frontend kann jederzeit weiter ausgebaut werden, um eine vollständig
professionelle UI zu erhalten.

------------------------------------------------------------------------

