
import java.io.Serializable;

class Item implements Serializable {

    private double price;
    private String name, description;

    public Item(String name, String description, double price) {
        this.price = price;
        this.name = name;
        this.description = description;
    }

    public double getPrice() {
        return this.price;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }
    

}