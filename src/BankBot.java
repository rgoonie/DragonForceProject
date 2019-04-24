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
    
        try{ importData(); }
        catch(Exception e){
            System.out.println("Unable to read bank.dat... Exiting");
            System.exit(0);
        }
        
    }

    public void makeOrderRequest() { add code }
    
    
    
    
//-----------------------Import/Export Functions--------------------------------
    
    private void importData() throws IOException{
        bankAccounts = new HashMap<>();
        
        Scanner file = new Scanner( new File("bank.dat") );        
        int amountData = file.nextInt();
        
        for(int i = 0; i < amountData; i++){
            file.nextLine();
            bankAccounts.put( file.next(), file.nextDouble() );
        }
        
        file.close();
    }
    
    public void exportData() {
        try{
            PrintWriter outFile = new PrintWriter("bank.dat");
            int dataAmount = bankAccounts.size();

            outFile.println(dataAmount);

            for(String key : bankAccounts.keySet()){
                outFile.println( key + " " + bankAccounts.get(key) );
            }

            outFile.close();
        }
        catch(Exception e){
            System.out.println("Unable to export bank data...");
        }
    }

}