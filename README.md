# Mini Proyecto -- Agenda e Inventarios Inteligentes 

Diseñar e implementar un módulo que gestione citas y stock aplicando ordenación(Burbuja, Selección, Inserción) y búsqueda (secuencial —primera, última, findAll,centinela— y binaria en arreglos ordenados).

-----
# Integrantes:
- Mark Gonzales
- Alejandro Padilla
- Steven Jumbo
- Gyna Yupanqui

-----
# Decisiones de diseño 

### Datasets: 
-  Citas_100.csv
-  Citas_100_casi_ordenadas.csv
-  Inventario_500_inverso.csv
-  Pacientes_500.csv


### Estructuras de datos
- *Arreglos*: utilizados para permitir acceso directo por índice, facilitando la ordenación y la búsqueda binaria.
- *Listas simplemente enlazadas (SLL)*: empleadas cuando se prioriza la flexibilidad en inserciones, usando únicamente búsqueda secuencial.

### Algoritmos de ordenación
- *Bubble*
- *Selection*
- *Insertion*

### Algoritmos de búsqueda
- *Búsqueda secuencial* (primera, última, findAll, centinela): utilizada cuando los datos no están ordenados o cuando se requieren múltiples coincidencias.
- *Búsqueda binaria*: aplicada únicamente en arreglos ordenados debido a su mayor eficiencia.

- -----
# Casos Borde 

- *Estructura vacía*: las búsquedas retornan “no encontrado” y la ordenación no ejecuta operaciones.
- *Un solo elemento*: la ordenación mantiene el valor y la búsqueda funciona correctamente.
- *Elementos duplicados*: `findAll` retorna todas las coincidencias; primera y última ocurrencia funcionan correctamente.
- *Elemento no existente*: el sistema maneja el caso sin errores.
- *Datos ya ordenados*: Inserción muestra mejor desempeño.
- *Datos en orden inverso*: se valida el correcto funcionamiento de los algoritmos con mayor costo computacional.
