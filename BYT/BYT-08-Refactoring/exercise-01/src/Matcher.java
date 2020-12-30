public class Matcher {
    public Matcher() {
    }

    public boolean match(int[] expected, int[] actual, int clipLimit, int delta) {

        // moved checking of length at the beginning
        if (actual.length != expected.length) {
            return false;
        }
        // parsed to one loop
        for (int i = 0; i < actual.length; i++) {
            if (actual[i] > clipLimit) {
                actual[i] = clipLimit;
            } else if (Math.abs(expected[i] - actual[i]) > delta) {
                return false;
            }
        }
        return true;
    }
}