package ed.u2.sorting;

public class SelectionSort {

    public static <T extends Comparable<T>> SortStats sort(T[] array) {
        SortStats stats = new SortStats("Selection Sort");
        if (array == null || array.length <= 1) return stats;

        long start = System.nanoTime();
        int n = array.length;

        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                stats.comparisons++; // Contamos comparación
                if (array[j].compareTo(array[minIdx]) < 0) {
                    minIdx = j;
                }
            }

            // Solo hacemos swap si el mínimo no está ya en su lugar
            if (minIdx != i) {
                swap(array, i, minIdx);
                stats.swapsOrMoves++; // Contamos intercambio
            }
        }

        stats.timeNs = System.nanoTime() - start;
        return stats;
    }

    private static <T> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}