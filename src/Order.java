
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

class Order implements Serializable {

    private static final long serialVersionUID = 1L;
    private String status, name, address, phone, card, authNumber;
    private Date date;
    private Cart cart;

    public Order(Cart cart, Date orderDate, String name, String phone, String address, String card, String auth) {
        this.status = "ORDERED";
        this.cart = cart;
        this.date = orderDate;
        
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.card = card;
        this.authNumber = auth;
    }

    /**
     * @return The status of the order
     */
    public String getStatus() {
        return this.status;
    }

    /**
     * Updates the status of the order
     * @param newStatus The status to be set
     */
    public void setStatus(String newStatus) {
        this.status = newStatus;
    }

    /**
     * @return Gets the authorization number
     */
    public String getAuthNumber() {
        return this.authNumber;
    }

    /**
     * @return The total price of the order
     */
    public double getTotal() {
        return this.cart.getTotal();
    }

    /**
     * @return The name on the order
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return The address that the order is to be shipped to
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * @return The phone number of the orderer
     */
    public String getPhone() {
        return this.phone;
    }

    /**
     * @return The contents of the order
     */
    public Cart getCart(){
        return this.cart;
    }
    
    /**
     * @return The time/date the order was placed
     */
    public Date getDate() {
        return this.date;
    }
    
    /**
     * Outputs the order details to the CLI
     */
    public void viewOrder() {
        HashMap<Item, Integer> items = this.cart.getItems();
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        System.out.println("--------------------------------------------------");
        System.out.println("Order Made On: " + dateFormat.format(date));
        System.out.println("Credit Card: " + card);
        System.out.println("Status: " + status + "\n");
        
        System.out.println( String.format("%-15s%15s%15s", "<Items>", "<Quantity>", "<Cost>") );
        for(Item item : items.keySet()) {
            System.out.println( String.format("%-15s%15d%15.2f", item.getName(), items.get(item), item.getPrice()*items.get(item)) );
        }
        System.out.println( String.format("\n%-15s%15s%15.2f", "Total Cost.....", "...............", this.cart.getTotal()) );
        System.out.println("--------------------------------------------------");
        
    }
    
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        return "Order from " + dateFormat.format(date);
    }
    

}