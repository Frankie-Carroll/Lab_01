import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class ProductReader {
    public static void main(String[] args) {
        Path in = Path.of("products.csv");
        ArrayList<Product> products = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(in)) {

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isBlank()) continue;
                {
                    Product p = Product.fromCSV(line);
                    products.add(p);

                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.printf("%-10s %-18s %-28s %-10s%n", "ID", "Name", "Description", "Cost");
        System.out.println("---------------------------------------------------------------------");
        for (Product p : products) {
            System.out.printf("%-10s %-18s %-28s %-10.2f%n",
                    p.getId(), p.getName(), p.getDescription(), p.getCost());

        }
        System.out.println("\nRead " + products.size() + " product(s) from " + in.toAbsolutePath());
    }
}

