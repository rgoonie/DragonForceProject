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
        boolean added = false;
        for(Item i : items.keySet()){
            if(i.getName().equals(item.getName())){
                items.put(i, items.get(i) + quantity);
                added = true;
                break;
            }
        }
        
        if( !added )
            items.put(item, quantity);
            
        updateTotal();
    }
  
    public void removeItem(Item item, int quantity){
        for(Item i : items.keySet()){
            if( i.getName().equals(item.getName()) ){
                items.put(i, items.get(i) - quantity);
                if(items.get(i) <= 0)
                    items.remove(i);
                break;
            }
        }
        
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