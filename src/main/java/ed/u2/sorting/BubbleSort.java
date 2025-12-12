package ed.u2.sorting; // <--- Debe coincidir con SortStats

public class BubbleSort {

    public static <T extends Comparable<T>> SortStats sort(T[] array) {
        SortStats stats = new SortStats("Bubble Sort");

        if (array == null || array.length <= 1) return stats;

        long start = System.nanoTime();
        int n = array.length;
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                stats.comparisons++;

                if (array[j].compareTo(array[j + 1]) > 0) {
                    swap(array, j, j + 1);
                    stats.swapsOrMoves++;
                    swapped = true;
                }
            }
            if (!swapped) break;
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