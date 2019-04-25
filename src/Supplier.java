import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
import java.util.ArrayList;
import java.util.Date;

class Supplier extends User implements SupplierConstants {
    
    private static final long serialVersionUID = 1L;
    private HashMap<Item, Integer> catalog;
    private ArrayList<Order> pendingOrders;
    
    public Supplier(ArrayList<Order> pendingOrders) {
        super();
        this.catalog = new HashMap<>();
        this.pendingOrders = pendingOrders;
        importItems();
    }
    
    public void processOrderDelivery(Order o, String status) {
        o.setStatus(status);
    }

    public boolean confirmShipment(Order o) {
        return o.getStatus().equals("SHIPPED");
    }

    public Order retireveOrder(Date date) {
        for(Order o : pendingOrders){
            if(o.getDate() == date) {
                return o;
            }
        }
        return new Order(new Cart(), new Date(), "", "", "");
    }

    public boolean checkAvalibility(Item item, int numberRequested) {
        return this.catalog.get(item) <= numberRequested;
        // return item.getQuantity() <= numberRequested;
    }
    
    public void displayCatalog() {
        System.out.println("\n-----------Catalog----------");
        for(Item item: this.catalog.keySet()) {
            System.out.println( String.format("\nItem...........%s", item.getName()) );
            System.out.println( String.format("Description....%s", item.getDescription()) );
            System.out.println( String.format("Price..........%1.2f", item.getPrice()) );
        }
        System.out.println("\n----------------------------");
    }
    
    public Set<Item> getItems() {
        return this.catalog.keySet();
    }
    

//---------------------------Overriden Methods----------------------------------    
    
    @Override
    public int menu(Scanner in) {
        int selection = -1;
        
        System.out.println(MAIN_MENU);

        while(selection == -1) {
            System.out.print("Enter your choice (p, c, l):: ");

            String input = cleanInput(in.nextLine());
            while(input.equals(""))
                input += cleanInput(in.nextLine());

            switch(input.charAt(0)) {
                case PROCESS: selection = 6; break;
                case CONFIRM: selection = 7; break;
                case LOGOUT: selection = 1; break;

                default: System.out.println("'" + input.charAt(0) + "' is not a valid input - please try again" );
            }

        }

        return selection;
    }
    
//---------------------------Import/Export Functions----------------------------
    
    private void importItems() {
        try {
            Scanner file = new Scanner( new File(ITEMS_FILE) );
            while(file.hasNextLine()) {
                Item item = new Item(file.nextLine(), file.nextLine(), Double.parseDouble(file.nextLine()), 0);
                int amount = Integer.parseInt(file.nextLine());
                this.catalog.put(item, amount);
            }
            file.close();
        } catch(Exception e) {
            System.out.println(ERROR_IN_MSG);
            System.exit(0);
        }
    }
    
    private void exportItems() {
        try {
            PrintWriter outFile = new PrintWriter(ITEMS_FILE);
            for(Item key : this.catalog.keySet()) {
                outFile.println(key.getName());
                outFile.println(key.getDescription());
                outFile.println(key.getPrice());
                outFile.println( this.catalog.get(key) );
            }
            outFile.close();
        } catch(Exception e) {
            System.out.println(ERROR_OUT_MSG);
        }
    }


}