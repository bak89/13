package Model;

import java.util.Arrays;
import java.util.Random;

public class BinomialGenerator implements RandomGenerator {
    private Random random = new Random();
    private final int range;
    private final UserState level;

    public BinomialGenerator(UserState level, int range) {
        this.range = range;
        this.level = level;
    }
/*
    public int getInitialBlockX() {

        return this.random.nextInt(Settings.GRID_DIMENSION_X);
    }*/

    public int getRandomNumber() {

        int max = level.getLevel();
        int min = level.getLevel() - range;
        int randomNum = random.nextInt((max - min) + 1) + min;
        long[][] probabilities = this.getBinomialDistribution(min, max, max);

        if (level.getLevel() < 16) {
            Arrays.sort(probabilities[1]);
            this.reverseArray(probabilities[1]);
        }

        int p = 0;
        for (int i = 0; i < probabilities[0].length; i++) {
            p += probabilities[1][i];
            if (randomNum <= p) {
                return (int) probabilities[0][i];
            }
        }
        return min;
    }

    private void reverseArray(long[] array) {

        for (int i = 0; i < array.length / 2; i++) {
            long temp = array[i];
            array[i] = array[array.length - i - 1];
            array[array.length - i - 1] = temp;
        }
    }

    private long[][] getBinomialDistribution(int min, int max, long total) {

        int n = max - min;
        long[][] ret = new long[2][n + 1];
        int mean = (n + 1) / 2;
        float p = 1;
        if (n > 0) {
            p = (float) mean / (float) n;
        }

        long count = 0;
        for (int i = 0; i <= n; i++) {
            double p_i = combination(n, i) * Math.pow(p, i)
                    * Math.pow((1 - p), (n - i));
            long count_i = (long) (total * p_i);
            ret[0][i] = i + min;
            ret[1][i] = count_i;
            count += count_i;
        }

        while (count < total) {
            int i = random.nextInt(n + 1);
            ret[1][i]++;
            count++;
        }

        return ret;
    }

    private double combination(int n, int k) {
        double ret = 1;
        while (k > 0) {
            ret = ret * ((double) n / (double) k);
            k--;
            n--;
        }
        return ret;
    }

}
