
import java.io.Serializable;

class Item implements Serializable {

    private static final long serialVersionUID = 1L;
    private double price;
    private String name, description;
    private int quantity;

    public Item(String name, String description, double price, int quantity) {
        this.price = price;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
    }

    /**
     * @return The price of the item
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * @return The name of the item
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return The description of the item
     */
    public String getDescription() {
        return this.description;
    }
    
    /**
     * @return The amount of the item the supplier has
     */
    public int getQuantity() {
        return this.quantity;
    }

    /**
     * Removes a selected quantity of items if avaliable
     * @param num The number of the item to be removed from inventory
     * @return True if there were enough in stock. If true is returned the quantity was successfuly removed.
     */
    public boolean subtractQuantity(int num) {
        if(num > this.quantity) {
            return false;
        }
        this.quantity -= num;
        return true;
    }

}