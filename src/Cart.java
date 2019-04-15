import java.util.HashMap;
import java.util.Map;

class Cart {

    private double total;
    private HashMap<Item, Double> items;

    public Cart() {
        this.total = 0.0;
        this.items = new HashMap<Item, Double>();
    }

    /**
     * Addes an item of specified quantity to the cart
     * @param item The item to be added to the cart
     * @param quantity The amount of the item to be added
     */
    public void addItem(Item item, double quantity) {
        items.put(item, quantity);
        updateTotal();
    }

    /**
     * Removes an item from the cart
     * @param item The time to be removed from the cart
     */
    public void removeItem(Item item) {
        items.remove(item);
        updateTotal();
    }

    private void updateTotal() {
        total = 0.0;
        for(Map.Entry<Item, Double> item: items.entrySet()) {
            total += item.getKey().getPrice() * item.getValue();
        }
    }

    public double getTotal() {
        return total;
    }

}