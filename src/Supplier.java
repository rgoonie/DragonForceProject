import java.util.ArrayList;
import java.util.HashMap;

class Supplier extends User {

    HashMap<Item, Integer> catalog;

    public Supplier(String id, String password) {
        super(id, password);
    }

    public boolean createAccount() {
        return false;
    }

    public Order retrieveOrder() {
        return new Order(new ArrayList<Item>(), 0.0, "");
    }

    public Order selectOrder(Order order) {
        return order;
    }

    public String confirmShipment() {
        return "";
    }

    public boolean checkAvalibility(Item item) {
        return false;
    }

}