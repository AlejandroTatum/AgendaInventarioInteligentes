package ed.u2.controller;

import ed.u2.model.Cita;
import ed.u2.model.Insumo;
import ed.u2.model.Paciente;
import ed.u2.search.ArraySearch;
import ed.u2.search.SLLPacientes;
import ed.u2.sorting.BubbleSort;
import ed.u2.sorting.InsertionSort;
import ed.u2.sorting.SelectionSort;
import ed.u2.sorting.SortStats;
import ed.u2.util.CsvReader;
import ed.u2.view.ConsoleView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

public class HospitalController {
    private final ConsoleView view;

    private SLLPacientes listaPacientesGlobal;
    private Insumo[] inventarioGlobal;

    private static final String PATH_CITAS_CASI = "src/main/java/data/citas_100_casi_ordenadas.csv";
    private static final String PATH_PACIENTES = "src/main/java/data/pacientes_500.csv";
    private static final String PATH_INVENTARIO = "src/main/java/data/inventario_500_inverso.csv";

    public HospitalController(ConsoleView view) {
        this.view = view;
        cargarDatosEnMemoria();
    }

    private void cargarDatosEnMemoria() {
        // Carga inicial para la "Zona Docente"
        List<Paciente> lista = CsvReader.loadPacientesList(PATH_PACIENTES);
        listaPacientesGlobal = new SLLPacientes();
        for (Paciente p : lista) listaPacientesGlobal.add(p);

        inventarioGlobal = CsvReader.loadInventario(PATH_INVENTARIO);
        InsertionSort.sort(inventarioGlobal); // Pre-ordenamos para búsquedas binarias posteriores
    }

    public void iniciar() {
        view.animarTitulo("SISTEMA HOSPITALARIO - ESTRUCTURA DE DATOS");

        boolean continuar = true;
        while (continuar) {
            int opcion = view.mostrarMenuPrincipal();

            switch (opcion) {
                case 1:
                    ejecutarDemoTecnica();
                    view.pausar();
                    break;
                case 2:
                    ejecutarZonaDocente();
                    view.pausar();
                    break;
                case 0:
                    view.mostrarMensaje("Finalizando ejecución.");
                    continuar = false;
                    break;
                default:
                    view.mostrarAlerta("Opción no válida.");
            }
        }
    }

    private void ejecutarDemoTecnica() {
        view.mostrarSubtitulo("DEMOSTRACIÓN COMPLETA (Benchmark)");

        testAgendaCasiOrdenada();
        testPacientesReales();
        testInventarioInverso();

        view.mostrarMatrizDecision();

        view.mostrarExito("Proceso completado.");
    }

    private void ejecutarZonaDocente() {
        new SolicitudDocente(this).ejecutar();
    }

    private void testAgendaCasiOrdenada() {
        view.mostrarSubtitulo("1. AGENDA DE CITAS");
        // REQUERIMIENTO 4: MENCIONAR CSV
        view.mostrarMensaje("Archivo fuente: " + PATH_CITAS_CASI);

        view.mostrarAnalisisCaso("Datos casi ordenados (Agenda al día)", "Mejor Caso para Insertion Sort: O(n)");

        Cita[] citas = CsvReader.loadCitas(PATH_CITAS_CASI);
        if (citas.length == 0) return;

        view.mostrarCarga("Ejecutando comparativa (10 corridas)", 300);

        List<SortStats> stats = new ArrayList<>();
        // Guardamos una copia ordenada para usarla en la búsqueda por rangos
        Cita[] dataOrdenada = Arrays.copyOf(citas, citas.length);

        stats.add(ejecutarExperimento("Bubble Sort", () -> BubbleSort.sort(Arrays.copyOf(citas, citas.length))));
        // Usamos dataOrdenada aquí para que quede efectivamente ordenado por InsertionSort
        stats.add(ejecutarExperimento("Insertion Sort", () -> InsertionSort.sort(dataOrdenada)));
        stats.add(ejecutarExperimento("Selection Sort", () -> SelectionSort.sort(Arrays.copyOf(citas, citas.length))));

        view.mostrarResultadosOrdenacion(stats);
        view.imprimirContexto("Note cómo Insertion Sort tiene muy pocos movimientos (casi 0) y es el más rápido.");

        view.mostrarSubHeader("1.1 Búsqueda por Rango de Fechas");
        view.imprimirContexto("Usando LowerBound y UpperBound (Búsqueda Binaria)");

        // Definimos el rango: Buscar todas las citas del 2025-03-01 entre las 10:00 y las 14:00
        String inicioStr = "2025-03-01T10:00";
        String finStr    = "2025-03-01T14:00";

        Cita cInicio = new Cita("", "", inicioStr);
        Cita cFin    = new Cita("", "", finStr);

        view.mostrarMensaje("Buscando citas entre: " + inicioStr + " y " + finStr);

        int low = ArraySearch.lowerBound(dataOrdenada, cInicio);
        int high = ArraySearch.upperBound(dataOrdenada, cFin);

        view.mostrarMensaje("Índices encontrados -> Inicio: " + low + " | Fin: " + high);

        if (low < high) {
            view.mostrarExito("Citas encontradas en el rango:");
            for (int i = low; i < high; i++) {
                view.mostrarItem(dataOrdenada[i]);
            }
        } else {
            view.mostrarAlerta("No se encontraron citas en ese rango.");
        }
    }

    private void testPacientesReales() {
        view.mostrarSubtitulo("2. GESTIÓN DE PACIENTES");
        view.mostrarMensaje("Archivo fuente: " + PATH_PACIENTES);

        view.mostrarAnalisisCaso("Búsqueda en Lista Enlazada (SLL)", "Complejidad Lineal: O(n)");

        view.imprimirContexto("Se implementó búsqueda secuencial que se detiene en la primera coincidencia para agilizar el proceso.");

        String apellido = "Ramírez";
        procesarBusquedaPaciente(apellido);

        view.mostrarMensaje("Filtrado masivo (Prioridad 1):");
        List<Paciente> urgencias = listaPacientesGlobal.findAllByPrioridad(1);
        view.mostrarExito("Se recorrieron " + listaPacientesGlobal.getSize() + " nodos para encontrar " + urgencias.size() + " coincidencias.");
    }

    private void testInventarioInverso() {
        view.mostrarSubtitulo("3. INVENTARIO DE INSUMOS");
        view.mostrarMensaje("Archivo fuente: " + PATH_INVENTARIO);

        view.mostrarAnalisisCaso("Ordenación de Datos Inversos", "Peor Caso para Insertion Sort: O(n^2)");

        Insumo[] raw = CsvReader.loadInventario(PATH_INVENTARIO);
        view.mostrarCarga("Ordenando '500 al 1' a '1 al 500' (Comparativa)", 800);

        List<SortStats> stats = new ArrayList<>();
        stats.add(ejecutarExperimento("Bubble Sort", () -> BubbleSort.sort(Arrays.copyOf(raw, raw.length))));
        stats.add(ejecutarExperimento("Insertion Sort", () -> InsertionSort.sort(Arrays.copyOf(raw, raw.length))));
        stats.add(ejecutarExperimento("Selection Sort", () -> SelectionSort.sort(Arrays.copyOf(raw, raw.length))));

        view.mostrarResultadosOrdenacion(stats);
        view.imprimirContexto("Evidencia: Insertion Sort sufre con datos inversos (muchos movimientos). Selection Sort es más estable aquí.");

        view.mostrarSubHeader("3.1 Búsqueda Binaria Exacta");
        view.mostrarAnalisisCaso("Búsqueda Binaria (En array ordenado)", "Mejor Caso / Logarítmico: O(log n)");

        procesarBusquedaStock(450);
    }

    public void procesarBusquedaPaciente(String apellido) {
        view.mostrarMensaje(">>> Buscando: '" + apellido + "'");

        long start = System.nanoTime();
        Paciente p = listaPacientesGlobal.findFirstByApellido(apellido);
        long end = System.nanoTime();

        double ms = (end - start) / 1_000_000.0;
        view.mostrarMensaje(String.format("Método: Secuencial (First) | Tiempo: %.4f ms", ms));

        if (p != null) {
            view.mostrarExito("ENCONTRADO:");
            view.mostrarItem(p);
        } else {
            view.mostrarAlerta("No encontrado.");
        }
    }

    public void procesarBusquedaStock(int cantidad) {
        view.mostrarMensaje(">>> Buscando Stock: " + cantidad);

        Insumo clave = new Insumo("", "", cantidad);
        List<Long> tiempos = new ArrayList<>();
        int idx = -1;

        // Medición precisa (10 corridas)
        for(int i=0; i<10; i++) {
            long start = System.nanoTime();
            idx = ArraySearch.binarySearch(inventarioGlobal, clave);
            long end = System.nanoTime();
            tiempos.add(end - start);
        }

        Collections.sort(tiempos);
        long medianaNs = (tiempos.get(4) + tiempos.get(5)) / 2;
        double medianaMs = medianaNs / 1_000_000.0;

        view.mostrarMensaje(String.format("Método: Binaria | Tiempo (Mediana): %.4f ms", medianaMs));

        if (idx >= 0) {
            view.mostrarExito("ENCONTRADO (Índice " + idx + "):");
            view.mostrarItem(inventarioGlobal[idx]);
        } else {
            view.mostrarAlerta("No existe insumo con ese stock.");
        }
    }

    private SortStats ejecutarExperimento(String nombre, Supplier<SortStats> funcion) {
        List<Long> tiempos = new ArrayList<>();
        SortStats ultimo = null;
        for (int i = 0; i < 10; i++) {
            SortStats s = funcion.get();
            tiempos.add(s.timeNs);
            ultimo = s;
        }
        Collections.sort(tiempos);
        // Promediamos los centrales
        ultimo.timeNs = (tiempos.get(4) + tiempos.get(5)) / 2;
        ultimo.algorithmName = nombre;
        return ultimo;
    }
}