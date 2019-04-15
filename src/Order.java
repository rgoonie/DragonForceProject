class Order {

    private String status;
    private double authNumber, total;
    private Cart cart;

    public Order(Cart cart, String cardNumber, Bank bank) {
        this.status = "PENDING";
        this.total = cart.getTotal();
        this.cart = cart;
        this.authNumber = bank.processOrder(cardNumber, cart.getTotal());
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

    public double getAuthNumber() {
        return this.authNumber;
    }

    public double getTotal() {
        return this.total;
    }

}