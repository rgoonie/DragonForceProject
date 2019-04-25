
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

abstract class User implements Serializable {

    private static final long serialVersionUID = 1L;
    protected String id, password;

    public User() {
        this("", "");
    }
    
    public User(String id, String password) {
        this.id = id;
        this.password = password;
    }

    /**
     * Creates a new User account
     * Same for all user since only someone not signed in will use this
     */
    final void createAccount(Scanner in, HashMap<String, String> signInInfo, HashMap<String, Customer> customerAccount, HashMap<String, ArrayList<Order>> orderData) {
        char choice = 'X';
        while(choice != 'c' && choice != 's') {
            System.out.print("\nAre you a [c]ustomer or [s]upplier:: ");
            String input = cleanInput(in.nextLine());
            while(input.equals(""))
                input += cleanInput(in.nextLine());
            choice = input.charAt(0);            
        }
        
        System.out.print("Enter an ID (case sensitive):: ");
        String newID = in.nextLine();
        while( signInInfo.get(newID) != null ) {
            System.out.println("\nThis id is already taken... Please Try Again");
            System.out.print("Enter an ID (case sensitive):: ");
            newID = in.nextLine();
        }
        
        System.out.print("Enter a password (case sensitive, at least 8 characters):: ");
        String pass = in.nextLine();
        while(pass.length() < 8) {
            System.out.println("\nThis password is not long enough... Please Try Again");
            System.out.print("Enter a password (case sensitive, at least 8 characters):: ");
            pass = in.nextLine();
        }
        
        
        if(choice == 'c') {
            System.out.print("Enter your name:: ");
            String name = in.nextLine();
            
            String phone = "";
            while(phone.equals("")) {
                System.out.print("Enter your phone number:: ");
                phone = in.nextLine().replace('(', ' ').replace(')', ' ').replace('-', ' ').replace(" ", "");
                
                try {
                    Long.parseLong(phone);
                } catch(Exception e) {
                    System.out.println("The phone number is not vaild... Please Try Again\n");
                    phone = "";
                }
            }
            
            System.out.print("Enter your address:: ");
            String address = in.nextLine().replace(" ", "").toUpperCase();
            while( address.equals("") )
                address += in.nextLine().replace(" ", "").toUpperCase();
            
            String credit = "";
            while(credit.equals("")) {
                System.out.print("Enter your credit card number:: ");
                credit = in.nextLine().replace('-', ' ').replace(" ", "");
                
                try {
                    Long.parseLong(credit);
                } catch(Exception e) {
                    System.out.println("The credit card number is not vaild... Please Try Again\n");
                    credit = "";
                }
            }
            
            signInInfo.put(newID, pass);
            customerAccount.put( newID, new Customer(newID, pass, phone, address, name, credit) );
            orderData.put(newID, new ArrayList<Order>());
        } else {
            signInInfo.put(newID, pass);
        }
        
    }

    /**
     * Sign in existing User
     * Same for all users as both suppliers and customers need to have id and pass
     */
    final void signIn(Scanner in, String[] out) {
        System.out.print("\nEnter User ID:: ");
        out[0] = in.nextLine();
        System.out.print("Enter Password:: ");
        out[1] = in.nextLine();
    }
    
    /**
     * Runs menu logic for user
     * @return command chosen by user
     */
    abstract int menu(Scanner in);

    protected String cleanInput(String inpt) {
        return inpt.replace(" ", "").toLowerCase();
    }
    
}