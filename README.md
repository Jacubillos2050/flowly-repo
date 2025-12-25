# Flowly_MC

> **Sistema de Gestión de Pedidos (Order Management System OMS)**  
> Simula un entorno de comercio electrónico empresarial distribuido.  
> Gestión simplificada y en tiempo real de pedidos multicanal (web, marketplace, tienda física).  
> Ideal para empresas con alta rotación y equipos distribuidos.

Este proyecto sigue los principios de **Domain-Driven Design (DDD)** y está construido con una **arquitectura de microservicios**, enfocándose en la claridad del dominio, la separación de responsabilidades y la escalabilidad.

---

## 🧱 Arquitectura

- **Estilo arquitectónico**: Microservicios
- **Enfoque**: Domain-Driven Design (DDD)
    - Bounded Contexts claramente delimitados (ej: `OrderContext`, `InventoryContext`, `CustomerContext`)
    - Entities, Value Objects, Aggregates, Repositories y Domain Services
    - Aplicación de patrones como CQRS y Event-Driven Architecture (opcional en fases posteriores)
- **Comunicación entre servicios**: RESTful APIs + (futuro) eventos asíncronos (ej: con Kafka o RabbitMQ)

---

## 🛠️ Stack Tecnológico

| Capa                     | Tecnología                                     |
|--------------------------|-----------------------------------------------|
| Sistema operativo        | Windows 11                                    |
| Entorno de desarrollo    | IntelliJ IDEA Community Edition               |
| Lenguaje                 | Java 17+                                      |
| JDK                      | [Eclipse Temurin JDK 17+ (Adoptium)](https://adoptium.net/) |
| Gestión de versiones     | Git + Git Bash                                |
| Contenedores             | Podman (sin Docker Desktop)                   |
| Base de datos            | MariaDB (por contenedor con Podman)           |
| Cliente de base de datos | DBeaver Community                             |
| Pruebas de API           | Insomnia                                      |
| Construcción             | Maven o Gradle (a definir)                    |

---

## 📐 Style Guide

Para mantener consistencia en todos los microservicios y facilitar la colaboración, se aplican las siguientes reglas:

### 🔤 Convenciones de nombrado (Java + DDD)

| Elemento                | Estilo          | Ejemplo                                  |
|-------------------------|-----------------|------------------------------------------|
| Clases (Entities, Services, etc.) | `PascalCase` | `OrderAggregate`, `PaymentService`       |
| Variables y métodos     | `camelCase`     | `orderId`, `calculateTotal()`            |
| Constantes              | `UPPER_SNAKE_CASE` | `MAX_ORDER_ITEMS`, `DEFAULT_STATUS`    |
| Paquetes                | `lowercase`     | `com.flowlymc.order.domain`              |
| Archivos de código      | `PascalCase.java` | `OrderRepository.java`                 |

### 🧱 Estructura de carpetas (por microservicio)
