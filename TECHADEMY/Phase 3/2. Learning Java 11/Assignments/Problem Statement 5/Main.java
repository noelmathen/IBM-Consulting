import java.util.*;

// Custom Exceptions
class PriceException extends Exception {
    public PriceException(String message) { super(message); }
}

class EssentialCommodityException extends Exception {
    public EssentialCommodityException(String message) { super(message); }
}

class GradeMismatchException extends Exception {
    public GradeMismatchException(String message) { super(message); }
}

// Item class
class Item {
    private Integer id;
    private String name;
    private Double purchasedPrice;
    private Double salesPrice;
    private String grade;

    public Item(Integer id, String name, Double purchasedPrice, Double salesPrice, String grade) {
        this.id = id;
        this.name = name;
        this.purchasedPrice = purchasedPrice;
        this.salesPrice = salesPrice;
        this.grade = grade;
    }

    // Getters and setters
    public Integer getId() { return id; }
    public String getName() { return name; }
    public Double getPurchasedPrice() { return purchasedPrice; }
    public Double getSalesPrice() { return salesPrice; }
    public String getGrade() { return grade; }

    public void setId(Integer id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setPurchasedPrice(Double purchasedPrice) { this.purchasedPrice = purchasedPrice; }
    public void setSalesPrice(Double salesPrice) { this.salesPrice = salesPrice; }
    public void setGrade(String grade) { this.grade = grade; }

    @Override
    public String toString() {
        return String.format("%-5d %-20s %-10.2f %-10.2f %-5s",
                id, name, purchasedPrice, salesPrice, grade);
    }

    // Override equals and hashCode to check uniqueness by id
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item = (Item) o;
        return Objects.equals(id, item.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

// Service class
class ItemService {
    public Set<Item> collectAllItems(List<String> itemStrings) {
        Set<Item> items = new HashSet<>();
        for (String itemStr : itemStrings) {
            try {
                Item item = parseItem(itemStr);

                // Validation
                if (!item.getGrade().equals("N") && !item.getGrade().equals("E")) {
                    throw new GradeMismatchException("Grade must be N or E");
                }

                if (item.getSalesPrice() <= item.getPurchasedPrice()) {
                    throw new PriceException("Sales price must be greater than purchase price");
                }

                if (item.getGrade().equals("E")) {
                    double maxSalesPrice = item.getPurchasedPrice() * 1.25;
                    if (item.getSalesPrice() > maxSalesPrice) {
                        throw new EssentialCommodityException("Sales price cannot exceed 125% of purchase price for Essential Commodity");
                    }
                }

                // Add item to set - duplicates automatically rejected by equals/hashCode
                boolean added = items.add(item);
                if (!added) {
                    System.out.println("Duplicate item id found and rejected: " + item.getId());
                }

            } catch (Exception e) {
                System.out.println("Error processing item: " + itemStr);
                System.out.println("Reason: " + e.getMessage());
            }
        }
        return items;
    }

    private Item parseItem(String input) throws Exception {
        // Expected format: id,name,purchasedPrice,salesPrice,grade
        String[] parts = input.split(",");
        if (parts.length != 5) throw new Exception("Invalid input format");

        Integer id = Integer.parseInt(parts[0].trim());
        String name = parts[1].trim();
        Double purchasedPrice = Double.parseDouble(parts[2].trim());
        Double salesPrice = Double.parseDouble(parts[3].trim());
        String grade = parts[4].trim();

        return new Item(id, name, purchasedPrice, salesPrice, grade);
    }
}

// Main class
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ItemService service = new ItemService();

        System.out.print("Enter number of items: ");
        int n = sc.nextInt();
        sc.nextLine(); // consume newline

        List<String> inputs = new ArrayList<>();

        System.out.println("Enter item details (id,name,purchasedPrice,salesPrice,grade):");
        for (int i = 0; i < n; i++) {
            String line = sc.nextLine();
            inputs.add(line);
        }

        Set<Item> acceptedItems = service.collectAllItems(inputs);

        System.out.println("\nAccepted items:");
        System.out.printf("%-5s %-20s %-10s %-10s %-5s\n", "ID", "Name", "Purchased", "Sales", "Grade");
        for (Item item : acceptedItems) {
            System.out.println(item);
        }

        sc.close();
    }
}
