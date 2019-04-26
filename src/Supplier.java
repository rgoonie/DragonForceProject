import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
import java.util.ArrayList;

class Supplier extends User implements SupplierConstants {
    
    private static final long serialVersionUID = 1L;
    private HashMap<Item, Integer> catalog;
    
    public Supplier() {
        super();
        this.catalog = new HashMap<>();
        importItems();
    }
    
    public void processOrderDelivery(Scanner in, ArrayList<Order> orderedStuff, ArrayList<Order> readiedStuff) {
        int i;
        System.out.println();
        for(i = 0; i<orderedStuff.size(); i++) {
            System.out.println( String.format("[%d] %s", i+1, orderedStuff.get(i).toString()) );
        }
        System.out.println("[e]xit");
        
        System.out.print("Which order do you want to process:: ");
        String input = cleanInput(in.nextLine());
        while(input.equals(""))
            input += cleanInput(in.nextLine());
        
        Integer selection = convertToInt(input);
        if(selection != null && (selection > orderedStuff.size() || selection < 1))
            selection = null;
        
        while(input.charAt(0) != 'e' && selection == null){
            System.out.println("'" + input + "' is not a valid input... please try again\n");
            System.out.print("Which order do you want to process:: ");
            input = cleanInput(in.nextLine());
            while(input.equals(""))
                input += cleanInput(in.nextLine());
            
            selection = convertToInt(input);
            if(selection != null && (selection > orderedStuff.size() || selection < 1))
                selection = null;
        }      
        
        if(input.charAt(0) == 'e'){
            return;
        } 
        
        else{
            Order orderToProcess = orderedStuff.get(selection - 1);
            HashMap<Item, Integer> items = orderToProcess.getCart().getItems();
            boolean canBeProcessed = true;
            
            for(Item item: items.keySet())
                canBeProcessed = checkAvalibility(item, items.get(item));
            
            if(canBeProcessed){
                updateCatalog(items);
                orderToProcess.setStatus("READY");
                readiedStuff.add( orderedStuff.remove(selection - 1) );
                System.out.println("The order has been processed and is ready to ship...\n");
            }
            else{
                System.out.println("This order cannot be processed at this time...");
                System.out.println("---OUT OF STOCK ITEMS---");
                for(Item item: items.keySet())
                    if( checkAvalibility(item, items.get(item)) == false )
                        System.out.println(item.getName());
                
                System.out.println();
            }
        }
    }

    public void confirmShipment(Scanner in, ArrayList<Order> readiedStuff) {
        int i;
        System.out.println();
        for(i = 0; i<readiedStuff.size(); i++) {
            System.out.println( String.format("[%d] %s", i+1, readiedStuff.get(i).toString()) );
        }
        System.out.println("[e]xit");
        
        System.out.print("Which order do you want to confirm shipment:: ");
        String input = cleanInput(in.nextLine());
        while(input.equals(""))
            input += cleanInput(in.nextLine());
        
        Integer selection = convertToInt(input);
        if(selection != null && (selection > readiedStuff.size() || selection < 1))
            selection = null;
        
        while(input.charAt(0) != 'e' && selection == null){
            System.out.println("'" + input + "' is not a valid input... please try again\n");
            System.out.print("Which order do you want to confirm shipment:: ");
            input = cleanInput(in.nextLine());
            while(input.equals(""))
                input += cleanInput(in.nextLine());
            
            selection = convertToInt(input);
             if(selection != null && (selection > readiedStuff.size() || selection < 1))
                selection = null;
        }      
        
        if(input.charAt(0) == 'e'){
            return;
        } 
        
        else{
            Order orderToProcess = readiedStuff.get(selection - 1);
            orderToProcess.setStatus("SHIPPED");
            
            readiedStuff.remove(selection -1);
            System.out.println("The order has been shipped...\n");
        }
    }

    public boolean checkAvalibility(Item item, int numberRequested) {
        for(Item i : catalog.keySet()){
            if(i.getName().equals(item.getName()))
                return catalog.get(i) >= numberRequested;
        }
        return false;

        //return catalog.get(item) >= numberRequested;
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
    
    private void updateCatalog(HashMap<Item, Integer> orderedItems){
        for(Item o : orderedItems.keySet())
            for(Item c : catalog.keySet()){
                if(o.getName().equals(c.getName())){
                    catalog.put(c, catalog.get(c) - orderedItems.get(o));
                    break;
                }
            }
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

                default: System.out.println("'" + input.charAt(0) + "' is not a valid input - please try again\n" );
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
    
    public void exportItems() {
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