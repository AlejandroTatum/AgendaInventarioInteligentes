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
import java.util.List;

public class HospitalController {
    private ConsoleView view;

    // Ajusta las rutas si es necesario
    private static final String PATH_CITAS = "src/main/java/data/citas_100.csv";
    private static final String PATH_CITAS_CASI = "src/main/java/data/citas_100_casi_ordenadas.csv";
    private static final String PATH_PACIENTES = "src/main/java/data/pacientes_500.csv";
    private static final String PATH_INVENTARIO = "src/main/java/data/inventario_500_inverso.csv";

    public HospitalController(ConsoleView view) {
        this.view = view;
    }

    public void iniciar() {
        view.mostrarHeader("SISTEMA HOSPITALARIO VETERINARIO - ESTRUCTURA DE DATOS");

        testAgendaCasiOrdenada();
        testPacientesReales();
        testInventarioInverso();
    }

    private void testAgendaCasiOrdenada() {
        view.mostrarHeader("1. MÓDULO AGENDA (Datos Casi Ordenados)");
        view.mostrarMensaje("Archivo: " + PATH_CITAS_CASI);

        Cita[] citas = CsvReader.loadCitas(PATH_CITAS_CASI);
        if(citas.length == 0) { view.mostrarAlerta("No se cargaron citas."); return; }

        view.mostrarExito("Registros cargados: " + citas.length);

        List<SortStats> stats = new ArrayList<>();
        Cita[] dataBubble = Arrays.copyOf(citas, citas.length);
        Cita[] dataInsert = Arrays.copyOf(citas, citas.length);
        Cita[] dataSelect = Arrays.copyOf(citas, citas.length);

        stats.add(BubbleSort.sort(dataBubble));
        stats.add(InsertionSort.sort(dataInsert));
        stats.add(SelectionSort.sort(dataSelect));

        view.mostrarResultadosOrdenacion(stats);
        view.mostrarMensaje("Análisis: Insertion Sort aprovecha el pre-orden existente.");
    }

    private void testPacientesReales() {
        view.mostrarHeader("2. MÓDULO PACIENTES (Lista Enlazada)");
        view.mostrarMensaje("Archivo: " + PATH_PACIENTES);

        List<Paciente> lista = CsvReader.loadPacientesList(PATH_PACIENTES);
        SLLPacientes sll = new SLLPacientes();
        for(Paciente p : lista) sll.add(p);

        view.mostrarExito("Pacientes en SLL: " + sll.getSize());

        view.mostrarSubtitulo("Búsqueda Secuencial: 'Ramírez'");
        Paciente primero = sll.findFirstByApellido("Ramírez");
        Paciente ultimo = sll.findLastByApellido("Ramírez");

        if(primero != null) view.mostrarItem("[Primera Aparición] " + primero);
        if(ultimo != null)  view.mostrarItem("[Última Aparición]  " + ultimo);

        view.mostrarSubtitulo("Búsqueda FindAll: Prioridad 1 (Urgencia)");
        List<Paciente> urgencias = sll.findAllByPrioridad(1);
        view.mostrarExito("Total encontrados: " + urgencias.size());
    }

    private void testInventarioInverso() {
        view.mostrarHeader("3. MÓDULO INVENTARIO (Datos Inversos)");
        view.mostrarMensaje("Archivo: " + PATH_INVENTARIO);

        Insumo[] insumos = CsvReader.loadInventario(PATH_INVENTARIO);
        view.mostrarExito("Items cargados: " + insumos.length);
        view.mostrarAlerta("Estado original: Orden Descendente (Peor caso para Bubble/Insertion).");

        // Ordenamos
        long start = System.nanoTime();
        InsertionSort.sort(insumos);
        long end = System.nanoTime();
        double ms = (end - start) / 1_000_000.0;

        view.mostrarMensaje(String.format("Ordenado por Stock en %.3f ms", ms));

        // Búsqueda Binaria
        int stockBuscado = 450;
        view.mostrarSubtitulo("Búsqueda Binaria: Stock " + stockBuscado);

        Insumo clave = new Insumo("", "", stockBuscado);
        int idx = ArraySearch.binarySearch(insumos, clave);

        if (idx >= 0) {
            view.mostrarExito("Encontrado en índice: " + idx);
            view.mostrarItem(insumos[idx]);
        } else {
            view.mostrarAlerta("No encontrado.");
        }

        // Búsqueda fallida
        view.mostrarSubtitulo("Prueba Fallida: Stock 9999");
        if (ArraySearch.binarySearch(insumos, new Insumo("", "", 9999)) == -1) {
            view.mostrarExito("Correctamente identificado como no existente.");
        }
    }
}