public class Product {
    private final String id;
    private String name;
    private String description;
    private double cost;

    /**
     * Construct a product
     * @param id
     * @param name
     * @param description
     * @param cost
     */
    public Product(String id, String name, String description, double cost) {
        if (id == null) throw new IllegalArgumentException("id null");
        id = id.trim();
        if (id.isEmpty()) throw new IllegalArgumentException("id blank");
        this.id = id;

        setName(name);
        setDescription(description);
        setCost(cost);
    }

    public String toCSV(){
        return id + "," + name + "," + description + "," + cost;
    }

    public String toJSON() {
        return "{"
                + "\"id\":\"" + id + "\","
                + "\"name\":\"" + name + "\","
                + "\"description\":\"" + description + "\","
                + "\"cost\":" + cost
                + "}";
    }

    public String toXML() {
        return "<Product>"
                + "<id>" + id + "</id>"
                + "<name>" + name + "</name>"
                + "<description>" + description + "</description>"
                + "<cost>" + cost + "</cost>"
                + "</Product>";
    }

    public static Product fromCSV (String csvLine){
        String[] parts = csvLine.split(",", -1); // keep empty fields
        if (parts.length != 4) {
            throw new IllegalArgumentException("Bad CSV: " + csvLine);
        }
        String id = parts[0].trim();
        String name = parts[1].trim();
        String description = parts[2].trim();
        double cost = Double.parseDouble(parts[3].trim());
        return new Product(id, name, description, cost);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return id + " " + name + "($" + cost + ")";
    }
}
