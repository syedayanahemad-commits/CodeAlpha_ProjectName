import java.io.*;

public class FileManager {

    public static void savePortfolio(
            Portfolio portfolio)
            throws IOException {

        BufferedWriter writer =
                new BufferedWriter(
                        new FileWriter(
                                "portfolio.txt"));

        writer.write(
                String.valueOf(
                        portfolio.getCashBalance()));

        writer.close();
    }
}