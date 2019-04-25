import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

class Cart implements Serializable{

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
        if( items.get(item) != null)
            items.put(item, items.get(item) + quantity);
        else
            items.put(item, quantity);
            
        updateTotal();
    }
    
    public void removeItem(Item item, int quantity){
        items.put(item, items.get(item) - quantity);
        if(items.get(item) <= 0)
            items.remove(item);
        
        updateTotal();
    }
    
    private void updateTotal() {
        total = 0.0;
        for(Map.Entry<Item, Integer> item: items.entrySet()) {
            total += item.getKey().getPrice() * item.getValue();
        }
    }

    public double getTotal() {
        return total;
    }

    public HashMap<Item, Integer> getItems() {
        return this.items;
    }

}