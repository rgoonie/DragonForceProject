import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

class Supplier extends User {
    
    private HashMap<Item, Integer> catalog;
    
    public Supplier() {
        super();
        this.catalog = new HashMap<>();
        importItems();
    }
    
    public Supplier(String id, String password) {
        super(id, password);
    }
    
    public void processOrderDelivery() {}

    public void confirmShipment() {}

    public void retrieveOrder() {}

    public void selectOrder(Order order) {}

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
        
        System.out.println("\n\n------------Menu------------");
        System.out.println("[p]rocess delivery order");
        System.out.println("[c]onfirm shipment");
        System.out.println("[l]og out");

        while(selection == -1) {
            System.out.print("Enter your choice (p, c, l):: ");

            String input = in.nextLine().replace(" ", "").toLowerCase();
            while(input.equals(""))
                input += in.nextLine().replace(" ", "").toLowerCase();

            switch(input.charAt(0)) {
                case 'p': selection = 6; break;
                case 'c': selection = 7; break;
                case 'l': selection = 1; break;

                default: System.out.println("'" + input.charAt(0) + "' is not a valid input - please try again" );
            }

        }

        return selection;
    }
    
//---------------------------Import/Export Functions----------------------------
    
    private void importItems() {
        try {
            Scanner file = new Scanner( new File("items.dat") );
            while(file.hasNextLine()) {
                Item item = new Item(file.nextLine(), file.nextLine(), Double.parseDouble(file.nextLine()), 0);
                int amount = Integer.parseInt(file.nextLine());
                this.catalog.put(item, amount);
            }
            file.close();
        } catch(Exception e) {
            System.out.println("Unable to load catalog... Exiting...");
            System.exit(0);
        }
    }
    
    private void exportItems() {
        try {
            PrintWriter outFile = new PrintWriter("items.dat");
            for(Item key : this.catalog.keySet()) {
                outFile.println(key.getName());
                outFile.println(key.getDescription());
                outFile.println(key.getPrice());
                outFile.println( this.catalog.get(key) );
            }
            outFile.close();
        } catch(Exception e) {
            System.out.println("Unable to export item data...");
        }
    }


}