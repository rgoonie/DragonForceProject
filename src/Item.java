class Item {

    private double price;
    private String name, description;
    private int amount;

    public Item(Double price, String name, String description, int amount) {
        this.price = price;
        this.name = name;
        this.description = description;
        this.amount = amount;
    }

    public double getPrice() {
        return this.price;
    }

    public int getAmount() {
        return this.amount;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public void updateAmount(int newAmount) {
        this.amount = newAmount;
    }

}