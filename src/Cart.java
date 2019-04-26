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
                if(items.get(i) < 0 ){
                    items.put(i, Integer.MAX_VALUE);
                    System.out.println("Your cart contains the maximum amount of " + i.getName() + "(s)\n");
                }
                added = true;
                break;
            }
        }
        
        if( !added )
            items.put(item, quantity);
            
        updateTotal();
    }
  
    /**
     * Removes an item of a specified quantity to the users cart
     * @param item The item to be removed
     * @param quantity The amount of the item to be removed
     */
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
    
    /**
     * Recalculates the cart total
     */
    private void updateTotal() {
        this.total = 0.0;
        for(Map.Entry<Item, Integer> item: this.items.entrySet()) {
            this.total += item.getKey().getPrice() * item.getValue();
        }
    }

    /**
     * @return Returns the total cost of the users cart
     */
    public double getTotal() {
        return this.total;
    }

    /**
     * @return Returns the users cart
     */
    public HashMap<Item, Integer> getItems() {
        return this.items;
    }

}