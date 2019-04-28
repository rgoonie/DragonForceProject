# CS2365_OOP_Project

__Assumption:__
  1. There is only 1 supplier. This Shopping System will only sell what this supplier supplys.
  2. A supplier can have multiple accounts as a supplier can have multiple workers.
  3. While the Bank object should be an interface, it's processes are automated.
  4. A supplier will have at least 5 items. (Items are listed in the items.dat file)
  5. For menu interfaces that are expecting only one character, only the first character entered 
        will be evaluated excluding whitespace.
  6. User Interface is ran in the console.
  7. The maximum amount of items a custom is allowed to order in one order is Java's INTEGER.MAX_VALUE
  8. If a customer removes more items than they have in their cart, that item will just be 
        removed from the cart, although the console states that the amount was removed.
  9. If a customer tries to enter the same credit card number after being prompt to enter a new card
        during a transaction, the transaction will be cancelled.
 10. When an order is being processed and items are being reserved, the items will be taken out of the
        inventory count so a supplier does not try to ship an order that request more items than the supplier
        can supply.
 11. A supplier will only be able to process orders and confirm shipments
 
 12. The files that are required for the program to run is bank.dat and items.dat
    12. The items.dat file had the following formatting for items:
          item 1 name as String
          item 1 description as String
          item 1 price as Double
          inventory amount for item 1 as Integer
          item 2 name as String
          item 2 description as String
          ...
    12. The bank.dat file has the following formatting for bank accounts:

          [card 1 number as Integer] [card 1 amount as Double]
          [card 2 number as Integer] [card 2 amount as Double]
          ...
          
 13. If one of the following files are present, all of the following file will be present:
 
          log_in.dat .........(this file contains all usernames and passwords)
          customers.dat ......(this file contains usernames in the order that the customer objects are stored in customers.objects)
          customers.objects ..(this file contains the customer objects)
          orders.dat .........(this file contains usernames in the order that the Arraylist of Orders are stored in orders.objects)
          orders.objects .....(this file contains the ArrayList of Orders)
          
Features to be added:
  1. Allowing support for multiple suppliers with different item sets
  2. Storing data in an online database to allow multiple instance of the program to be ran
