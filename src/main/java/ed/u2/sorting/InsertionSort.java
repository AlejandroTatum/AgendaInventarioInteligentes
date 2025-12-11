package ed.u2.sorting;

public class InsertionSort {

    public static <T extends Comparable<T>> SortStats sort(T[] array) {
        SortStats stats = new SortStats("Insertion Sort");
        if (array == null || array.length <= 1) return stats;

        long start = System.nanoTime();
        int n = array.length;

        for (int i = 1; i < n; i++) {
            T key = array[i];
            int j = i - 1;

            while (j >= 0) {
                stats.comparisons++; // Contamos la comparación del while
                if (array[j].compareTo(key) > 0) {
                    array[j + 1] = array[j]; // Movimiento (desplazamiento)
                    stats.swapsOrMoves++;
                    j--;
                } else {
                    break; // Salimos si encontramos la posición correcta
                }
            }
            array[j + 1] = key;
        }

        stats.timeNs = System.nanoTime() - start;
        return stats;
    }
}