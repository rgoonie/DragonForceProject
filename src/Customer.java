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
    
    /**
     * 
     * @param in The CLI input method
     * @param items All the items in the shopping system
     */
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
            
            while(input.charAt(0) != EXIT && selection == null){
                System.out.println("'" + input + "' is not a valid input... please try again\n");
                System.out.print(ADD_ITEM_PROMPT);
                input = cleanInput(in.nextLine());
                while(input.equals(""))
                    input += cleanInput(in.nextLine());
                
                selection = convertToInt(input);
                if(selection != null && (selection > list.size() || selection < 1))
                    selection = null;
            } 

            if(input.charAt(0) == EXIT){
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
                this.cart.addItem(list.get(selection - 1), amount);
                System.out.println(amount + " " + list.get(selection - 1).getName() + "(s) was added to your cart.");
            }
        }
    }
    
    /**
     * Remove items menu
     * @param in The CLI input
     */
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
            
            while(input.charAt(0) != EXIT && selection == null) {
                System.out.println("'" + input + "' is not a valid input... please try again\n");
                System.out.print("Which item would you like to remove to cart:: ");
                input = in.nextLine().replace(" ", "").toLowerCase();
                while(input.equals(""))
                    input += in.nextLine().replace(" ", "").toLowerCase();
                
                selection = convertToInt(input);
                if(selection != null && (selection > list.size() || selection < 1))
                    selection = null;
            }

            if(input.charAt(0) == EXIT){
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
                cart.removeItem(list.get(selection - 1), amount);
                System.out.println(amount + " " + list.get(selection - 1).getName() + "(s) was removed from your cart.");
            }
        }
    }
    
    /**
     * Outputs the cart onto the CLI
     */
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
     * @param auth The authroization number
     */
    public Order makeOrderRequest(String auth) {
        Order res =  new Order( this.cart, new Date(), this.name, this.phoneNumber, this.address, this.creditCardNumber, auth );
        this.cart = new Cart();
        
        return res;
    }
    
    /**
     * Displays the users orders
     * @param in The CLI input
     * @param list A list of all the users orders
     */
    public void viewOrder(Scanner in, ArrayList<Order> list) {
    	int i;
        while(true) {
            if(list.isEmpty()){
                System.out.println("You have no orders...\n");
                return;
            }
            
            System.out.println();
            for(i = 0; i<list.size(); i++) {
                System.out.println( String.format("[%d] %s", i+1, list.get(i) ) );
            }
            System.out.println("[e]xit");

            System.out.print("Which order would you like to view:: ");
            String input = cleanInput(in.nextLine());
            while(input.equals(""))
                input += cleanInput(in.nextLine());

            Integer selection = convertToInt(input);
            if(selection != null && (selection > list.size() || selection < 1))
                selection = null;
            
            while(input.charAt(0) != EXIT && selection == null) {
                System.out.println("'" + input + "' is not a valid input... please try again\n");
                System.out.print("Which order would you like to view:: ");
                input = cleanInput(in.nextLine());
                while(input.equals(""))
                    input += cleanInput(in.nextLine());
                
                selection = convertToInt(input);
                if(selection != null && (selection > list.size() || selection < 1))
                    selection = null;
            }

            if(input.charAt(0) == EXIT){
                return;
            } else {
            	list.get(selection-1).viewOrder();
            }
            
            
        }
    }
    
    /**
     * Changes the users credit card number
     * @param in The CLI input
     * @return The new credit card number
     */
    public String changeCard(Scanner in){
        String credit = "";
        while(credit.equals("")) {
            System.out.print("Enter a credit card number or 'e' to exit:: ");
            
            credit = cleanInput(in.nextLine().replace('-', ' '));
            while( credit.equals("") )
                credit = cleanInput(in.nextLine().replace('-', ' '));

            try {
                if(credit.charAt(0) == EXIT){
                    return creditCardNumber;
                }
                Long.parseLong(credit);
            } catch(Exception e) {
                System.out.println("The credit card number is not vaild... Please Try Again\n");
                credit = "";
            }
        }
        
        this.creditCardNumber = credit;
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
            System.out.print("Enter your choice (a, r, s, m, v, l):: ");

            String input = cleanInput(in.nextLine());
            while(input.equals(""))
                input += cleanInput(in.nextLine());

            switch(input.charAt(0)){
                case ADD: selection =  3; break;
                case REMOVE: selection =  9; break;
                case SELECT: selection = 10; break;
                case MAKE: selection =  4; break;
                case VIEW: selection =  5; break;
                case LOGOUT: selection =  1; break;

                default: System.out.println("'" + input.charAt(0) + "' is not a valid input - please try again\n" );
            }

        }

        return selection;
    }
    
}