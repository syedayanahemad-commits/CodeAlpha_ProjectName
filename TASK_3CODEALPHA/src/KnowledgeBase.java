import java.io.*;
import java.util.*;

public class KnowledgeBase {

    private Map<String, String> knowledge = new HashMap<>();

    public KnowledgeBase() {

        try {

            BufferedReader br =
                    new BufferedReader(new FileReader("../knowledge.txt"));

            String line;

            while ((line = br.readLine()) != null) {

                String[] parts = line.split("=", 2);

                if (parts.length == 2) {
                    knowledge.put(parts[0].trim().toLowerCase(),
                                  parts[1].trim());
                }
            }

            br.close();

        } catch (Exception e) {

            System.out.println("Knowledge file not found.");
        }
    }

    public String search(String query) {

        query = query.toLowerCase();

        for (String key : knowledge.keySet()) {

            if (query.contains(key)) {
                return knowledge.get(key);
            }
        }

        return null;
    }
}