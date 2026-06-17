# FutMJI - Organizador de Campeonatos de Fútbol

**FutMJI** es una aplicación de escritorio desarrollada en Java utilizando la interfaz gráfica Swing y el IDE NetBeans. El sistema está diseñado para facilitar la gestión, registro y control de un torneo o campeonato de fútbol local, permitiendo administrar equipos, registrar partidos en tiempo real y calcular automáticamente la tabla de posiciones bajo las normativas estándar del deporte.

---

## Características Principales

- **Tabla de Posiciones Dinámica:** Visualización en tiempo real de los puntos, partidos jugados (PJ), ganados (PG), empatados (PE), perdidos (PP), goles a favor (GF), goles en contra (GC) y la diferencia de goles (DG).
- **Ordenamiento Automático:** La tabla clasifica de forma automática a los equipos de mayor a menor puntaje, aplicando los criterios estándar de competición.
- **Gestión de Equipos:** Registro completo de nuevos clubes participantes y opción de eliminación de equipos con limpieza de estadísticas.
- **Registro de Partidos:** Formulario interactivo para registrar los resultados de los encuentros, actualizando simultáneamente las estadísticas de ambos equipos involucrados (local y visitante).
- **Interfaz Moderna:** Incorporación del Look and Feel **FlatLaf** (FlatLightLaf) para brindar una experiencia visual limpia, fluida y profesional.

---

## Tecnologías y Herramientas Utilizadas

- **Lenguaje:** Java (Versión 11 o superior recomendada)
- **Paradigma:** Programación Orientada a Objetos (POO)
- **Interfaz Gráfica:** Java Swing / NetBeans GUI Builder
- **Librería de Estilos:** FlatLaf (Flat Light Look and Feel)
- **Gestor de Dependencias/Proyecto:** Ant

---

## Estructura del Proyecto (Arquitectura)

El software sigue una separación limpia de responsabilidades mediante paquetes para facilitar su mantenimiento:

- `Entities`: Contiene las clases de negocio o modelos de datos principales (ej. `Equipo.java`).
- `Services`: Aloja la lógica de control y las operaciones del campeonato (`ServicioCampeonato.java`). Encargado del procesamiento matemático de los puntos y goles.
- `Vista`: Formularios visuales basados en `JFrame` y componentes Swing (`VentanaPrincipal`, `VentanaRegistrarEquipo`, `VentanaRegistrarPartido`, `VentanaLogin`).

---

## Instalación y Ejecución

### Requisitos previos
- Java JDK 11 o superior
- NetBeans IDE (recomendado) o cualquier IDE Java
- Git (opcional, para clonar el repositorio)

### Pasos para ejecutar
1. Clona el repositorio:
   ```bash
   git clone https://github.com/Misamhub/Proyecto-Programacion-Il.git
