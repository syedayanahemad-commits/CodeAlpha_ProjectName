public class NLPProcessor {

    public static String preprocess(String input) {

        input = input.toLowerCase();

        input = input.replaceAll("[^a-zA-Z0-9 ]", "");

        return input.trim();
    }
}
