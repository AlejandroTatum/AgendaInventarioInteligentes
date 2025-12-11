package ed.u2.view;

import ed.u2.sorting.SortStats;
import java.util.List;

public class ConsoleView {

    // Colores ANSI (Opcional: Si tu consola no los soporta, déjalos como cadenas vacías "")
    private static final String RESET = "\u001B[0m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String CYAN = "\u001B[36m";
    private static final String BOLD = "\u001B[1m";

    public void mostrarHeader(String titulo) {
        System.out.println("\n" + CYAN + "╔══════════════════════════════════════════════════════════════════════╗");
        System.out.println("║ " + String.format("%-68s", titulo) + " ║");
        System.out.println("╚══════════════════════════════════════════════════════════════════════╝" + RESET);
    }

    public void mostrarSubtitulo(String sub) {
        System.out.println("\n" + BOLD + ">>> " + sub + RESET);
        System.out.println("------------------------------------------------------------------------");
    }

    public void mostrarResultadosOrdenacion(List<SortStats> stats) {
        System.out.println("\nResultados de Rendimiento:");
        System.out.println("┌──────────────────────┬─────────────┬─────────────┬─────────────┐");
        System.out.println("│ Algoritmo            │ Tiempo      │ Comparac.   │ Movimientos │");
        System.out.println("├──────────────────────┼─────────────┼─────────────┼─────────────┤");

        for (SortStats s : stats) {
            // Convertimos ns a ms para facilitar lectura
            double ms = s.timeNs / 1_000_000.0;
            String tiempoStr = String.format("%.3f ms", ms);

            System.out.printf("│ %-20s │ %11s │ %11d │ %11d │\n",
                    s.algorithmName, tiempoStr, s.comparisons, s.swapsOrMoves);
        }
        System.out.println("└──────────────────────┴─────────────┴─────────────┴─────────────┘");
    }

    public void mostrarMensaje(String msg) {
        System.out.println("  • " + msg);
    }

    public void mostrarExito(String msg) {
        System.out.println("  " + GREEN + "✔ " + msg + RESET);
    }

    public void mostrarAlerta(String msg) {
        System.out.println("  " + YELLOW + "⚠ " + msg + RESET);
    }

    public void mostrarItem(Object item) {
        System.out.println("    └─> " + item.toString());
    }
}