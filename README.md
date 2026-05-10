```md
# Agenda e Inventarios Inteligentes

Proyecto académico en Java para gestionar citas médicas, pacientes e inventario hospitalario usando estructuras de datos, algoritmos de búsqueda, algoritmos de ordenamiento y datasets en CSV.

## Descripción

Este proyecto fue desarrollado como parte de la materia de Estructura de Datos. El objetivo principal es aplicar algoritmos clásicos en un escenario práctico: un sistema hospitalario que trabaja con citas, pacientes e insumos.

El sistema permite cargar datos desde archivos CSV, ordenarlos, realizar búsquedas y comparar el comportamiento de diferentes algoritmos según el tipo de dataset.

## Características

- Carga de datasets desde archivos CSV.
- Gestión de citas médicas, pacientes e inventario.
- Implementación de algoritmos de ordenamiento:
  - Bubble Sort
  - Selection Sort
  - Insertion Sort
- Implementación de algoritmos de búsqueda:
  - Búsqueda secuencial
  - Primera ocurrencia
  - Última ocurrencia
  - Find All
  - Búsqueda con centinela
  - Búsqueda binaria
- Uso de arreglos y listas simplemente enlazadas.
- Análisis de casos borde:
  - estructuras vacías
  - un solo elemento
  - elementos duplicados
  - elementos no existentes
  - datos ordenados
  - datos en orden inverso

## Tecnologías utilizadas

- Java 21
- Maven
- Apache Commons CSV
- JUnit 5
- Programación orientada a objetos
- Estructuras de datos
- Algoritmos de búsqueda y ordenamiento

## Estructura del proyecto

```txt
src/main/java/
├── data/
│   ├── citas_100.csv
│   ├── citas_100_casi_ordenadas.csv
│   ├── inventario_500_inverso.csv
│   └── pacientes_500.csv
├── ed/u2/
│   ├── Main.java
│   ├── controller/
│   ├── model/
│   ├── search/
│   ├── sorting/
│   ├── util/
│   └── view/
```

## Cómo ejecutar el proyecto

### Requisitos

- Java 21
- Git

El proyecto incluye Maven Wrapper, por lo que no es necesario instalar Maven globalmente.

### Clonar el repositorio

```bash
git clone https://github.com/AlejandroTatum/AgendaInventarioInteligentes.git
cd AgendaInventarioInteligentes
```

### Compilar

```bash
./mvnw clean compile
```

### Ejecutar

```bash
./mvnw exec:java -Dexec.mainClass="ed.u2.Main"
```

## Datasets utilizados

| Dataset | Descripción |
|---|---|
| `citas_100.csv` | Citas médicas sin orden específico |
| `citas_100_casi_ordenadas.csv` | Citas médicas casi ordenadas |
| `inventario_500_inverso.csv` | Inventario hospitalario en orden inverso |
| `pacientes_500.csv` | Registro de pacientes |

## Algoritmos implementados

| Categoría | Algoritmos |
|---|---|
| Ordenamiento | Bubble Sort, Selection Sort, Insertion Sort |
| Búsqueda | Secuencial, primera ocurrencia, última ocurrencia, findAll, centinela, binaria |
| Estructuras | Arreglos, listas simplemente enlazadas |

## Decisiones de diseño

- Se utilizaron arreglos para permitir acceso directo por índice y facilitar la búsqueda binaria.
- Se utilizaron listas simplemente enlazadas cuando se prioriza la inserción dinámica de datos.
- La búsqueda binaria se aplica únicamente sobre datos ordenados.
- Los datasets permiten comparar el rendimiento de los algoritmos en distintos escenarios: datos casi ordenados, datos invertidos y datos con múltiples registros.

## Aprendizajes principales

- Comprender cuándo conviene usar búsqueda secuencial o búsqueda binaria.
- Identificar por qué la búsqueda binaria requiere datos previamente ordenados.
- Comparar el comportamiento de algoritmos de ordenamiento según el estado inicial de los datos.
- Organizar un proyecto Java usando paquetes, modelos, controladores, utilidades y vista por consola.
- Trabajar con archivos CSV como fuente de datos.

## Autores

- Alejandro Padilla
- Mark Gonzales
- Steven Jumbo
- Gyna Yupanqui
```