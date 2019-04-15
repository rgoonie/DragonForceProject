import java.util.ArrayList;
import java.util.Map;

class Customer extends User {

    private String phoneNumber, address, name, creditCardNumber;
    private Cart cart; 
    private ArrayList<Order> orders;
    private Bank bank;

    public Customer(String id, String password, String phoneNumber, String address, String name, String creditCardNumber, Bank bank) {
        super(id, password);
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.name = name;
        this.creditCardNumber = creditCardNumber;
        this.cart = new Cart();
        this.orders = new ArrayList<Order>();
        this.bank = bank;
    }

    /**
     * Places the order and empties the cart
     */
    public void orderItems() {
        orders.add(new Order(this.cart, this.creditCardNumber, this.bank));
        this.cart = new Cart();
    }

    public boolean createAccount() {
        return false;
    }

    /**
     * @return All the items in the users cart
     */
    public String viewItems() {
        String ret = "";
        //Iterate over evey item in cart and add line return at the end
        for(Map.Entry<Item, Double> item: cart.getItems().entrySet()) {
            ret += item.getKey() + "\n";
        }
        return ret;
    }

    public String getCardNumber() {
        return this.creditCardNumber;
    }

}