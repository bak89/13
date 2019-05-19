package Model;

public class BinomialGenerator implements RandomGenerator {
    private final int range;

    public BinomialGenerator(int range) {
        this.range = range;
    }

    private static int getBinomial(int n, double p) {//n è incluso
        int x = 0;
        for (int i = 0; i < n; i++) {
            if (Math.random() < p)
                x++;
        }
        return x;
    }

    public int getRandomNumber(int level) {
        int min = Math.max(level - range, 1);
        int n = level - min - 1;
        return min + getBinomial(n, Settings.PROBABILITY);
    }
}
