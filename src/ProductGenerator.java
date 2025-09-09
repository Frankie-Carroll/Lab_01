import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class ProductGenerator {
    public static void main(String[] args) {
        SafeInputObject in = new SafeInputObject();
        ArrayList<Product> products = new ArrayList<>();

        boolean done;
        do {
            String id = in.getNonZeroLenString("Enter ID");
            String name = in.getNonZeroLenString("Enter the name of the product");
            String description = in.getNonZeroLenString("Enter a description for your product");
            double cost = in.getDouble("Enter the cost of the product");

            products.add(new Product(id, name, description, cost));

            done = in.getYNConfirm("Are you done adding products?");
        } while(!done);

        Path out = Path.of("products.csv");

        try {
            BufferedWriter writer = Files.newBufferedWriter(out,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING,
                    StandardOpenOption.WRITE);

            for(Product p : products) {
                writer.write(p.toCSV());
                writer.newLine();
            }
            writer.close();
            System.out.println("Wrote " + products.size() + " products to: " + out.toAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

