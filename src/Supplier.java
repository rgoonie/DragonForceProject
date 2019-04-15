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
        return new Order(new Cart(), "", new Bank(new HashMap<String,Double>()));
    }

    public Order selectOrder(Order order) {
        return order;
    }

    public String confirmShipment() {
        return "";
    }

    public boolean checkAvalibility(Item item) {
        return item.getAmount() > 0;
    }

}