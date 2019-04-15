class Order {

    private String status;
    private double authNumber, total;
    private Cart cart;
    private Customer customer;

    public Order(Cart cart, double total, Customer customer, Bank bank) {
        this.status = "PENDING";
        this.total = total;
        this.cart = cart;
        this.customer = customer;
        this.authNumber = bank.processOrder(customer.getCardNumber(), cart.getTotal());
    }

    public Cart getCart() {
        return this.cart;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String newStatus) {
        this.status = newStatus;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public double getAuthNumber() {
        return this.authNumber;
    }

    public double getTotal() {
        return this.total;
    }

}