import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class PersonGenerator {
    public static void main(String[] args) {
        SafeInputObject in = new SafeInputObject();
        ArrayList<Person> people = new ArrayList<>();

        boolean done;
        do {
            String id = in.getNonZeroLenString("Enter ID");
            String firstName = in.getNonZeroLenString("Enter your first name");
            String lastName = in.getNonZeroLenString("Enter your last name");
            String title = in.getNonZeroLenString("Enter your title");
            int yearOfBrith = in.getRangedInt("What year were you born", 1000, 2025);

            people.add(new Person(id, firstName, lastName, title, yearOfBrith));

            done = in.getYNConfirm("Are you done adding persons?");
        } while(!done);

        Path out = Path.of("people.csv");

        try {
            BufferedWriter writer = Files.newBufferedWriter(out,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING,
                    StandardOpenOption.WRITE);

            for(Person p : people) {
                writer.write(p.toCSV());
                writer.newLine();
            }

            writer.close();
            System.out.println("Data file written");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
