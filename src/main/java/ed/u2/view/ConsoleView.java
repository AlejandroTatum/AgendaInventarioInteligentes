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

    // --- ANÁLISIS DE CASO (CORREGIDO: ALINEACIÓN MILIMÉTRICA) ---

    public void mostrarAnalisisCaso(String escenario, String complejidad) {
        // CÁLCULO DE ALINEACIÓN:
        // Ancho interno total = 14 (label " Escenario:   ") + 46 (valor) = 60 caracteres.
        // Borde superior: "╔" + 4(=) + 1( ) + 15(TXT) + 1( ) + 39(=) + "╗" = 60 internos.
        // Borde inferior: "╚" + 60(=) + "╝"

        String top    = "╔════ CASO DE ESTUDIO ═══════════════════════════════════════╗";
        String bottom = "╚════════════════════════════════════════════════════════════╝";

        System.out.println("\n  " + PURPLE + top + RESET);
        // Usamos %-46s para asegurar que el contenido rellene exactamente hasta el borde derecho
        System.out.println("  " + PURPLE + "║" + RESET + " Escenario:   " + String.format("%-46s", escenario) + PURPLE + "║" + RESET);
        System.out.println("  " + PURPLE + "║" + RESET + " Complejidad: " + String.format("%-46s", complejidad) + PURPLE + "║" + RESET);
        System.out.println("  " + PURPLE + bottom + RESET);
        esperar(300);
    }

    // --- MATRIZ DE DECISIÓN ---
    public void mostrarMatrizDecision() {
        System.out.println("\n  " + BOLD + "MATRIZ DE DECISIÓN (CONCLUSIONES):" + RESET);

        String border = "  ┌───────────────────────────────────────┬─────────────────────────────────────┐";
        System.out.println(border);
        System.out.println("  │ ESCENARIO                             │ ALGORITMO / ESTRUCTURA RECOMENDADA  │");
        System.out.println("  ├───────────────────────────────────────┼─────────────────────────────────────┤");

        imprimirFilaMatriz("Datos casi ordenados (Agenda)", "Insertion Sort (O(n))", GREEN);
        imprimirFilaMatriz("Búsquedas por Prioridad (Pacientes)", "SLL + FindAll", GREEN);
        imprimirFilaMatriz("Inventario Estático y Grande", "Sort(1 vez) + Búsqueda Binaria", GREEN);
        imprimirFilaMatriz("Datos Inversos (Peor caso)", "Selection Sort (Estable)", GREEN);

        System.out.println("  └───────────────────────────────────────┴─────────────────────────────────────┘");
        esperar(500);
    }

    private void imprimirFilaMatriz(String col1, String col2, String color) {
        System.out.printf("  │ %-37s │ " + color + "%-35s" + RESET + " │\n", col1, col2);
    }

    // --- TABLA DE RESULTADOS ---
    public void mostrarResultadosOrdenacion(List<SortStats> stats) {
        System.out.println("\n  " + BOLD + "Resultados (Mediana 10 corridas):" + RESET);

        String border = "  ┌──────────────────────┬──────────────┬─────────────────┬───────────────┐";
        String middle = "  ├──────────────────────┼──────────────┼─────────────────┼───────────────┤";
        String bottom = "  └──────────────────────┴──────────────┴─────────────────┴───────────────┘";

        System.out.println(border);
        System.out.printf("  │ %-20s │ %-12s │ %-15s │ %-13s │\n", "Algoritmo", "Tiempo (ms)", "Comparaciones", "Movimientos");
        System.out.println(middle);

        for (SortStats s : stats) {
            double ms = s.timeNs / 1_000_000.0;
            System.out.printf("  │ %-20s │ %12.3f │ %,15d │ %,13d │\n",
                    s.algorithmName, ms, s.comparisons, s.swapsOrMoves);
            esperar(50);
        }
        System.out.println(bottom);
    }

    // --- MÉTODOS VISUALES GENÉRICOS ---

    public void animarTitulo(String titulo) {
        System.out.println("\n" + CYAN + "╔══════════════════════════════════════════════════════════════════════╗");
        System.out.print("║ ");
        imprimirEfectoMaquina(String.format("%-68s", titulo));
        System.out.println(" ║");
        System.out.println("╚══════════════════════════════════════════════════════════════════════╝" + RESET);
    }

    public void mostrarSubtitulo(String sub) {
        System.out.println("\n" + BOLD + ">>> " + sub + RESET);
        System.out.println("------------------------------------------------------------------------");
        esperar(100);
    }

    public void mostrarSubHeader(String msg) {
        System.out.println("\n  " + CYAN + "--- " + msg + " ---" + RESET);
    }

    public void mostrarCarga(String accion, int tiempoTotalMs) {
        System.out.print("  " + YELLOW + "⚡ " + accion + " " + RESET);
        int pasos = 10;
        int espera = Math.min(tiempoTotalMs, 500) / pasos;
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

    public void mostrarMensaje(String msg) { System.out.println("  • " + msg); }
    public void mostrarExito(String msg) { System.out.println("  " + GREEN + "✔ " + msg + RESET); }
    public void mostrarAlerta(String msg) { System.out.println("  " + RED + "✖ " + msg + RESET); }

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
        try { Thread.sleep(ms); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }
}