import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

class Cart implements Serializable{

    private static final long serialVersionUID = 1L;
    private double total;
    private HashMap<Item, Integer> items;

    public Cart() {
        this.total = 0.0;
            this.items = new HashMap<>();
    }

    /**
     * Adds an item of specified quantity to the cart
     * @param item The item to be added to the cart
     * @param quantity The amount of the item to be added
     */
    public void addItem(Item item, int quantity) {
        if(this.items.get(item) != null)
            this.items.put(item, this.items.get(item) + quantity);
        else
            this.items.put(item, quantity);
        updateTotal();
    }

    /**
     * Removes an item from the cart
     * @param item The time to be removed from the cart
     */
    public void removeItem(Item item) {
        this.items.remove(item);
        updateTotal();
    }

    private void updateTotal() {
        this.total = 0.0;
        for(Map.Entry<Item, Integer> item: this.items.entrySet()) {
            this.total += item.getKey().getPrice() * item.getValue();
        }
    }

    public double getTotal() {
        return this.total;
    }

    public HashMap<Item, Integer> getItems() {
        return this.items;
    }

}