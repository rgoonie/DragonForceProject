import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

class Customer extends User implements Serializable, CustomerConstants {

    private static final long serialVersionUID = 1L;
    private String phoneNumber, address, name, creditCardNumber;
    private Cart cart;

    public Customer(String id, String password, String phoneNumber, String address, String name, String creditCardNumber) {
        super(id, password);
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.name = name;
        this.creditCardNumber = creditCardNumber;
        
        this.cart = new Cart();        
    }
    
    public void selectItems(Scanner in, Set<Item> items) {
        ArrayList<Item> list = new ArrayList<>(items);
        int i =0;
        while(true) {
            System.out.println();
            for(i = 0; i<list.size(); i++) {
                System.out.println( String.format("[%d] %s", i+1, list.get(i).getName() ) );
            }
            System.out.println("[e]xit");

            System.out.print(ADD_ITEM_PROMPT);
            String input = cleanInput(in.nextLine());
            while(input.equals(""))
                input += cleanInput(in.nextLine());

            while(input.charAt(0) != 'e' && (input.charAt(0)< 49 || input.charAt(0) > 48+i )) {
                System.out.println("\n'" + input.charAt(0) + "' is not a valid input... please try again");
                System.out.print(ADD_ITEM_PROMPT);
                input = cleanInput(in.nextLine());
                while(input.equals(""))
                    input += cleanInput(in.nextLine());
            }

            if(input.charAt(0) == 'e'){
                return;
            } else {
                int amount = -1;
                while(amount == -1) {
                    System.out.print("How many would you like:: ");
                    try {
                        amount = Integer.parseInt(in.nextLine());

                        if(amount <0)
                            throw new Exception();
                    } catch(Exception e) {
                        amount = -1;
                        System.out.println("\nInvalid amount... please try again");
                    }
                }

                if(amount == 0) {
                    System.out.println("Cancelled addition to cart...");
                    continue;
                }                
                this.cart.addItem(list.get(input.charAt(0) - 49), amount);
                System.out.println(amount + " " + list.get(input.charAt(0) - 49).getName() + "(s) was added to your cart.");
            }
        }
    }

    /**
     * Places the order and empties the cart
     */
    public Order makeOrderRequest(String auth) {
        Order res =  new Order( this.cart, new Date(), this.name, this.phoneNumber, this.address, auth );
        this.cart = new Cart();
        
        return res;
    }
    
    public void viewOrder() {
        HashMap<Item, Integer> items = this.cart.getItems();
        
        System.out.println(NEW_LINE);
        System.out.println( String.format("%-15s%15s%15s", "<Items>", "<Quantity>", "<Cost>") );
        for(Item item : items.keySet()) {
            System.out.println( String.format("%-15s%15d%15.2f", item.getName(), items.get(item), item.getPrice()*items.get(item)) );
        }
        System.out.println( String.format("\n%-15s%15s%15.2f", "Total Cost.....", "...............", this.cart.getTotal()) );
        System.out.println(END_LINE);
    }

    /**
     * @return All the items in the users cart
     */
    public void viewItems() {
    }
    
    public String changeCard(Scanner in){
        String credit = "";
        while(credit.equals("")) {
            System.out.print("Enter a credit card number or 'e' to exit:: ");
            
            credit = in.nextLine().replace('-', ' ').replace(" ", "");
            while( credit.equals("") )
                credit = in.nextLine().replace('-', ' ').replace(" ", "").toLowerCase();

            try {
                if(credit.charAt(0) == 'e'){
                    return creditCardNumber;
                }
                Long.parseLong(credit);
            } catch(Exception e) {
                System.out.println("The credit card number is not vaild... Please Try Again\n");
                credit = "";
            }
        }
        
        creditCardNumber = credit;
        return credit;
    }

//---------------------------Get Methods----------------------------------------    
    
    public String getName(){ return name; }
    public String getPhoneNumber(){ return phoneNumber; }
    public String getAddress(){ return address; };
    public String getCreditCard(){ return creditCardNumber; }
    public double getCartCost(){ return cart.getTotal(); }

//---------------------------Overriden Methods----------------------------------

    @Override
    int menu(Scanner in) {
        int selection = -1;
        
        System.out.println(MENU);

        while(selection == -1) {
            System.out.print("Enter your choice (s, m, v, l):: ");

            String input = cleanInput(in.nextLine());
            while(input.equals(""))
                input += cleanInput(in.nextLine());

            switch(input.charAt(0)){
                case SELECT: selection = 3; break;
                case MAKE: selection = 4; break;
                case VIEW: selection = 5; break;
                case LOGOUT: selection = 1; break;

                default: System.out.println("'" + input.charAt(0) + "' is not a valid input - please try again" );
            }

        }

        return selection;
    }
    
}