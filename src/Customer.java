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

            Integer selection = convertToInt(input);
            if(selection != null && (selection > list.size() || selection < 1))
                selection = null;
            
            while(input.charAt(0) != 'e' && selection == null){
                System.out.println("'" + input + "' is not a valid input... please try again\n");
                System.out.print(ADD_ITEM_PROMPT);
                input = cleanInput(in.nextLine());
                while(input.equals(""))
                    input += cleanInput(in.nextLine());
                
                selection = convertToInt(input);
                if(selection != null && (selection > list.size() || selection < 1))
                    selection = null;
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
                        System.out.println("Invalid amount... please try again\n");
                    }
                }

                if(amount == 0) {
                    System.out.println("Cancelled addition to cart...");
                    continue;
                }                
                this.cart.addItem(list.get(input.charAt(0) - 49), amount);
                System.out.println(amount + " " + list.get(selection - 1).getName() + "(s) was added to your cart.");
            }
        }
    }
    
    public void removeItems(Scanner in){
        int i;
        while(true) {
            ArrayList<Item> list = new ArrayList<>(cart.getItems().keySet());
            if(list.isEmpty()){
                System.out.println("Your cart is empty...\n");
                return;
            }
            
            viewCart();
            System.out.println();
            for(i = 0; i<list.size(); i++) {
                System.out.println( String.format("[%d] %s", i+1, list.get(i).getName() ) );
            }
            System.out.println("[e]xit");

            System.out.print("Which item would you like to remove to cart:: ");
            String input = in.nextLine().replace(" ", "").toLowerCase();
            while(input.equals(""))
                input += in.nextLine().replace(" ", "").toLowerCase();

            Integer selection = convertToInt(input);
            if(selection != null && (selection > list.size() || selection < 1))
                selection = null;
            
            while(input.charAt(0) != 'e' && selection == null) {
                System.out.println("'" + input + "' is not a valid input... please try again\n");
                System.out.print("Which item would you like to remove to cart:: ");
                input = in.nextLine().replace(" ", "").toLowerCase();
                while(input.equals(""))
                    input += in.nextLine().replace(" ", "").toLowerCase();
                
                selection = convertToInt(input);
                if(selection != null && (selection > list.size() || selection < 1))
                    selection = null;
            }

            if(input.charAt(0) == 'e'){
                return;
            } else {
                int amount = -1;
                while(amount == -1) {
                    System.out.print("How many would you like to remove:: ");
                    try {
                        amount = Integer.parseInt(in.nextLine());

                        if(amount <0)
                            throw new Exception();
                    } catch(Exception e) {
                        amount = -1;
                        System.out.println("Invalid amount... please try again\n");
                    }
                }

                if(amount == 0) {
                    System.out.println("Cancelled removal from cart...");
                    continue;
                }                
                cart.removeItem(list.get(input.charAt(0) - 49), amount);
                System.out.println(amount + " " + list.get(input.charAt(0) - 49).getName() + "(s) was removed from your cart.");
            }
        }
    }
    
    public void viewCart(){
        HashMap<Item, Integer> items = cart.getItems();
        
        System.out.println("\n-----------------------Cart-----------------------");
        System.out.println( String.format("%-15s%15s%15s", "<Items>", "<Quantity>", "<Cost>") );
        for(Item item : items.keySet()) {
            System.out.println( String.format("%-15s%15d%15.2f", item.getName(), items.get(item), item.getPrice()*items.get(item)) );
        }
        System.out.println( String.format("\n%-15s%15s%15.2f", "Total Cost.....", "...............", cart.getTotal()) );
        System.out.println("--------------------------------------------------");
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
        
        System.out.println("\n\n------------Menu------------");
        System.out.println("[a]dd items to cart");
        System.out.println("[r]emove items from cart");
        System.out.println("[s]how cart");
        System.out.println("[m]ake order request");
        System.out.println("[v]iew order");
        System.out.println("[l]og out");

        while(selection == -1) {
            System.out.print("Enter your choice (a, r, s, m, v, l):: ");

            String input = cleanInput(in.nextLine());
            while(input.equals(""))
                input += cleanInput(in.nextLine());

            switch(input.charAt(0)){
                case 'a': selection =  3; break;
                case 'r': selection =  9; break;
                case 's': selection = 10; break;
                case 'm': selection =  4; break;
                case 'v': selection =  5; break;
                case 'l': selection =  1; break;

                default: System.out.println("'" + input.charAt(0) + "' is not a valid input - please try again\n" );
            }

        }

        return selection;
    }
    
}