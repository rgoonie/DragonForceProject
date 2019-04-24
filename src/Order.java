
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

class Order implements Serializable{

    private String status, name, address, phone, authNumber;
    private Date date;
    private Cart cart;

    public Order(Cart cart, Date orderDate, String name, String phone, String address) {
        this.status = "PENDING";
        this.cart = cart;
        this.date = orderDate;
        
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String newStatus) {
        this.status = newStatus;
    }

    public String getAuthNumber() {
        return this.authNumber;
    }

    public double getTotal() {
        return this.cart.getTotal();
    }
    
    public void viewOrder(){
        HashMap<Item, Integer> items = cart.getItems();
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        System.out.println("--------------------------------------------------");
        System.out.println("Order Made On: " + dateFormat.format(date) + "\n");
        System.out.println("Status:        " + status + "\n");
        
        System.out.println( String.format("%-15s%15s%15s", "<Items>", "<Quantity>", "<Cost>") );
        for(Item item : items.keySet()){
            System.out.println( String.format("%-15s%15d%15.2f", item.getName(), items.get(item), item.getPrice()*items.get(item)) );
        }
        System.out.println( String.format("\n%-15s%15s%15.2f", "Total Cost.....", "...............", cart.getTotal()) );
        System.out.println("--------------------------------------------------");
        System.out.println("\n");
        
    }
    
    public String toString(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        return "Order from " + dateFormat.format(date);
    }
    

}