import java.util.Calendar;
import java.util.Objects;

/**
 * A simple Person record with name fields, title, and year of birth.
 */
public class Person {
    private String id;
    private String firstName;
    private String lastName;
    private String title;     // e.g., "Mr.", "Ms.", "Dr."
    private int yob;          // year of birth (e.g., 2001)

    /**
     * Construct a Person.
     * @param id unique id
     * @param firstName first name
     * @param lastName last name
     * @param title title
     * @param yob year of birth
     */
    public Person(String id, String firstName, String lastName, String title, int yob) {
        setId(id);
        setFirstName(firstName);
        setLastName(lastName);
        setTitle(title);
        setYob(yob);
    }

    // ----- Required “additional methods” -----


    public String fullName() {
        return firstName + " " + lastName;
    }


    public String formalName() {
        return (title == null || title.isBlank())
                ? fullName()
                : title.trim() + " " + fullName();
    }

    public String getAge() {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        return String.valueOf(Math.max(0, currentYear - yob));
    }


    public String getAge(int year) {
        return String.valueOf(Math.max(0, year - yob));
    }


    public String toCSV() {

        return id + "," + firstName + "," + lastName + "," + title + "," + yob;
    }


    public String toJSON() {
        return "{"
                + "\"id\":\"" + id + "\","
                + "\"firstName\":\"" + firstName + "\","
                + "\"lastName\":\"" + lastName + "\","
                + "\"title\":\"" + title + "\","
                + "\"yob\":" + yob
                + "}";
    }


    public String toXML() {
        return "<Person>"
                + "<id>" + id + "</id>"
                + "<firstName>" + firstName + "</firstName>"
                + "<lastName>" + lastName + "</lastName>"
                + "<title>" + title + "</title>"
                + "<yob>" + yob + "</yob>"
                + "</Person>";
    }

    /**
     * Build a Person from a CSV record with 5 fields:
     * firstName,lastName,title,yob
     * @param csvLine CSV line
     * @return Person instance
     * @throws IllegalArgumentException if parsing fails
     */
    public static Person fromCSV(String csvLine) {
        String[] parts = csvLine.split(",", -1);
        if (parts.length != 5) {
            throw new IllegalArgumentException("Bad CSV line: " + csvLine);
        }
        String id = parts[0].trim();
        String firstName = parts[1].trim();
        String lastName = parts[2].trim();
        String title = parts[3].trim();
        int YOB = Integer.parseInt(parts[4].trim());
        return new Person(id, firstName, lastName, title, YOB);
    }

    private static String escapeCSV(String s) {
        if (s == null) return "";
        if (s.contains(",") || s.contains("\"")) {
            return "\"" + s.replace("\"", "\"\"") + "\"";
        }
        return s;
    }
    private static String escapeJSON(String s) {
        if (s == null) return "";
        return s.replace("\\", "\\\\").replace("\"", "\\\"");
    }
    private static String escapeXML(String s) {
        if (s == null) return "";
        return s.replace("&","&amp;").replace("<","&lt;").replace(">","&gt;")
                .replace("\"","&quot;").replace("'","&apos;");
    }

    public String getId() {return id;}
    public void setId(String id){
        this.id = id;
    }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) {
        this.title = title;
    }

    public int getYob() { return yob; }
    public void setYob(int yob) {
        this.yob = yob;
    }

    @Override public String toString() { return (id + " " + formalName() + yob);}
}
