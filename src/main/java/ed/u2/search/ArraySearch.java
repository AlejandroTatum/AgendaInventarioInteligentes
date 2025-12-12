package ed.u2.search;

public class ArraySearch {

    public static <T extends Comparable<T>> int findFirst(T[] array, T key) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].compareTo(key) == 0) return i;
        }
        return -1;
    }

    public static <T extends Comparable<T>> int findSentinel(T[] array, T key) {
        int n = array.length;
        // Creamos un array temporal con un espacio extra para el centinela
        // Nota: En C++ esto se hace sobre memoria reservada, en Java es menos natural.
        Object[] temp = new Object[n + 1];
        System.arraycopy(array, 0, temp, 0, n);

        temp[n] = key; // Colocar centinela
        int i = 0;

        @SuppressWarnings("unchecked")
        Comparable<T> current = (Comparable<T>) temp[i];

        while (current.compareTo(key) != 0) {
            i++;
            current = (Comparable<T>) temp[i];
        }

        if (i < n) return i;
        return -1;
    }


    public static <T extends Comparable<T>> int binarySearch(T[] array, T key) {
        int low = 0, high = array.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int cmp = array[mid].compareTo(key);
            if (cmp == 0) return mid;
            if (cmp < 0) low = mid + 1;
            else high = mid - 1;
        }
        return -1;
    }


    public static <T extends Comparable<T>> int lowerBound(T[] array, T key) {
        int low = 0, high = array.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (array[mid].compareTo(key) < 0) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }


    public static <T extends Comparable<T>> int upperBound(T[] array, T key) {
        int low = 0, high = array.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (array[mid].compareTo(key) <= 0) { // La diferencia principal con lowerBound
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }
}