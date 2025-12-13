package ed.u2.view;

import ed.u2.sorting.SortStats;
import java.util.List;
import java.util.Scanner;

public class ConsoleView {

    private final Scanner scanner;

    // Colores ANSI
    private static final String RESET = "\u001B[0m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String CYAN = "\u001B[36m";
    private static final String BLUE = "\u001B[34m";
    private static final String RED = "\u001B[31m";
    private static final String PURPLE = "\u001B[35m";
    private static final String BOLD = "\u001B[1m";

    public ConsoleView() {
        this.scanner = new Scanner(System.in);
    }

    // --- MÉTODOS DE MENÚ ---

    public int mostrarMenuPrincipal() {
        System.out.println("\n  " + BOLD + "SELECCIONE EL MODO DE EJECUCIÓN:" + RESET);
        System.out.println("  " + CYAN + "[1]" + RESET + " Demostración Técnica (Análisis de Casos)");
        System.out.println("  " + CYAN + "[2]" + RESET + " Zona de Pruebas (Consultas del Docente)");
        System.out.println("  " + CYAN + "[0]" + RESET + " Salir");
        System.out.print("\n  >> Ingrese opción: ");

        try {
            String input = scanner.nextLine();
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public void pausar() {
        System.out.println("\n  (Presione ENTER para continuar...)");
        scanner.nextLine();
    }

    // --- NUEVO: MOSTRAR ANÁLISIS DE CASO ---

    public void mostrarAnalisisCaso(String escenario, String complejidad) {
        System.out.println("\n  " + PURPLE + "╔════ CASO DE ESTUDIO ════════════════════════════════════════╗" + RESET);
        System.out.println("  " + PURPLE + "║" + RESET + " Escenario:   " + String.format("%-46s", escenario) + PURPLE + "║" + RESET);
        System.out.println("  " + PURPLE + "║" + RESET + " Complejidad: " + String.format("%-46s", complejidad) + PURPLE + "║" + RESET);
        System.out.println("  " + PURPLE + "╚═════════════════════════════════════════════════════════════╝" + RESET);
        esperar(500);
    }

    // --- REQUERIMIENTO 2: MATRIZ DE DECISIÓN ---
    public void mostrarMatrizDecision() {
        System.out.println("\n  " + BOLD + "MATRIZ DE DECISIÓN (CONCLUSIONES):" + RESET);
        System.out.println("  ┌───────────────────────────────────────┬──────────────────────────────────┐");
        System.out.println("  │ ESCENARIO                             │ ALGORITMO / ESTRUCTURA RECOMENDADA│");
        System.out.println("  ├───────────────────────────────────────┼──────────────────────────────────┤");
        System.out.println("  │ Datos casi ordenados (Agenda)         │ " + GREEN + "Insertion Sort" + RESET + " (O(n))           │");
        System.out.println("  │ Búsquedas por Prioridad (Pacientes)   │ " + GREEN + "SLL + FindAll" + RESET + "                    │");
        System.out.println("  │ Inventario Estático y Grande          │ " + GREEN + "Sort(1 vez) + Búsqueda Binaria" + RESET + "   │");
        System.out.println("  │ Datos Inversos (Peor caso)            │ " + GREEN + "Evitar Insertion Sort" + RESET + "            │");
        System.out.println("  └───────────────────────────────────────┴──────────────────────────────────┘");
        esperar(500);
    }

    // --- MÉTODOS VISUALES ---

    public void animarTitulo(String titulo) {
        System.out.println("\n" + CYAN + "╔══════════════════════════════════════════════════════════════════════╗");
        System.out.print("║ ");
        imprimirEfectoMaquina(String.format("%-68s", titulo));
        System.out.println(" ║");
        System.out.println("╚══════════════════════════════════════════════════════════════════════╝" + RESET);
    }

    public void mostrarSubtitulo(String sub) {
        System.out.println();
        System.out.println(BOLD + ">>> " + sub + RESET);
        System.out.println("------------------------------------------------------------------------");
        esperar(100);
    }

    public void mostrarCarga(String accion, int tiempoTotalMs) {
        System.out.print("  " + YELLOW + "⚡ " + accion + " " + RESET);
        int tiempoReal = Math.min(tiempoTotalMs, 500);
        int pasos = 10;
        int espera = tiempoReal / pasos;

        System.out.print("[");
        for (int i = 0; i < pasos; i++) {
            System.out.print("▓");
            esperar(espera);
        }
        System.out.println("] " + GREEN + "OK" + RESET);
    }

    public void imprimirContexto(String msg) {
        System.out.println(BLUE + "  [INFO] " + RESET + msg);
        esperar(300);
    }

    public void mostrarResultadosOrdenacion(List<SortStats> stats) {
        System.out.println("\n  " + BOLD + "Resultados (Mediana 10 corridas):" + RESET);
        System.out.println("  ┌──────────────────────┬─────────────┬─────────────────┬─────────────┐");
        System.out.println("  │ Algoritmo            │ Tiempo (ms) │ Comparaciones   │ Movimientos │");
        System.out.println("  ├──────────────────────┼─────────────┼─────────────────┼─────────────┤");

        for (SortStats s : stats) {
            double ms = s.timeNs / 1_000_000.0;
            System.out.printf("  │ %-20s │ %11.3f │ %15d │ %11d │\n",
                    s.algorithmName, ms, s.comparisons, s.swapsOrMoves);
            esperar(50);
        }
        System.out.println("  └──────────────────────┴─────────────┴─────────────────┴─────────────┘");
    }

    public void mostrarMensaje(String msg) {
        System.out.println("  • " + msg);
    }

    public void mostrarExito(String msg) {
        System.out.println("  " + GREEN + "✔ " + msg + RESET);
    }

    public void mostrarAlerta(String msg) {
        System.out.println("  " + RED + "✖ " + msg + RESET); // Rojo para alertas graves
    }

    public void mostrarItem(Object item) {
        System.out.println("    └─> " + item.toString());
    }

    private void imprimirEfectoMaquina(String texto) {
        for (char c : texto.toCharArray()) {
            System.out.print(c);
            esperar(2);
        }
    }

    private void esperar(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}