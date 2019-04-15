import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

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
        this.orders.add(new Order(this.cart, this.creditCardNumber, this.bank));
        this.cart = new Cart();
    }

    public boolean createAccount() {
        Scanner sc = new Scanner(System.in);
        boolean success = true;
        String inpID, inpPassword, inpPhoneNumber, inpAddress, inpName, inpCreditCardNumber;
        try {
            System.out.print("Enter an ID: ");
            inpID = sc.nextLine();
            System.out.print("\nEnter a password: ");
            inpPassword = sc.nextLine();
            System.out.print("\nEnter your address: ");
            inpAddress = sc.nextLine();
            System.out.print("\nEnter your phone number: ");
            inpPhoneNumber = sc.nextLine();
            System.out.print("\nEnter your credit card number: ");
            inpCreditCardNumber = sc.nextLine();
        } catch(Exception e) {
            success = false;
        } finally {
            sc.close();
        }
        return success;
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