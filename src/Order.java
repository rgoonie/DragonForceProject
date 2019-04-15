import java.util.ArrayList;

class Order {

    private String status, customerId;
    private double authNumber, total;
    private ArrayList<Item> order;

    public Order(ArrayList<Item> order, double total, String customerId) {
        this.status = "PENDING";
        this.customerId = customerId;
        this.authNumber = 0.0;
        this.total = total;
        this.order = order;
    }

}