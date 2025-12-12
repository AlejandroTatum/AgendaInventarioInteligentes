package ed.u2.sorting;

public class SortStats {
    public String algorithmName;
    public long comparisons;
    public long swapsOrMoves;
    public long timeNs;

    public SortStats(String algorithmName) {
        this.algorithmName = algorithmName;
        this.comparisons = 0;
        this.swapsOrMoves = 0;
        this.timeNs = 0;
    }

    @Override
    public String toString() {
        return String.format("%-15s | Comp: %-6d | Swaps/Movs: %-6d | Tiempo: %d ns",
                algorithmName, comparisons, swapsOrMoves, timeNs);
    }
}