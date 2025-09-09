
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class PersonReader {
    public static void main(String[] args) {
        Path in = Path.of("people.csv");
        ArrayList<Person> people = new ArrayList<>();

        try {BufferedReader reader = Files.newBufferedReader(in, StandardCharsets.UTF_8);
                String line;
                while((line = reader.readLine()) != null) {
                    if   (line.isBlank()) continue;

                    Person p = Person.fromCSV(line);
                    people.add(p);
                }

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.printf("%-8s %-15s %-15s %-15s %-5s%n", "ID#", "First Name", "Last Name", "Title", "YOB");
        System.out.println("-----------------------------------------------------");
        for (Person p : people) {
            System.out.printf("%-8s %-15s %-15s %-15s %-5s%n",
                    p.getId(), p.getFirstName(), p.getLastName(), p.getTitle(), p.getYob());
        }
        System.out.println("\nRead " + people.size() + " records from " + in.toAbsolutePath());
    }
}
