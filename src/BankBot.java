    /**
 * BankBot is an automated system. BankBot holds information pertaining to the
 *      bank (i.e. Card Numbers and Amount of money for account). The processes
 *      for the bank are automated.
 * 
 * Assumptions:
 *      There is only one bank that people will use.
 */
import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.HashMap;

public class BankBot {

    private HashMap<String, Double> bankAccounts; //Holds card numbers and connected amounts

    public BankBot() {
    
        try { 
            importData();
        } catch(Exception e) {
            System.out.println("Unable to read bank.dat... Exiting");
            System.exit(0);
        }
        
    }

    /**
     * Creates the order request and subtracts the cost from the users account
     * @param card The credit card number of the client
     * @param cost The cost of the total cart
     * @return The order number
     */
    public String makeOrderRequest(String card, double cost) { 
        
        Double account = bankAccounts.get(card);
        if( account != null && account - cost >= 0 ){
            bankAccounts.put(card, account - cost);
            return String.format("%d", (int)(Math.random() * 10000));
        }
        
        return null;
    }
    
    
    
    
//-----------------------Import/Export Functions--------------------------------
    
    private void importData() throws IOException {
        this.bankAccounts = new HashMap<>();
        
        Scanner file = new Scanner( new File("bank.dat") );
        
        while(file.hasNextLine()) {
            bankAccounts.put( file.next(), Double.parseDouble(file.nextLine()) );
        }
        
        file.close();
    }
    
    public void exportData() {
        try {
            PrintWriter outFile = new PrintWriter("bank.dat");

            for(String key : bankAccounts.keySet()) {
                outFile.println( key + " " + bankAccounts.get(key) );
            }

            outFile.close();
        } catch(Exception e) {
            System.out.println("Unable to export bank data...");
        }
    }

}