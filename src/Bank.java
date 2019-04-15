import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

class Bank {

    private HashMap<String, Double> userInfo; // Hashmap is String: Card number double: Amount associated with card

    public Bank(HashMap<String, Double> userInfo) {
        this.userInfo = userInfo; 
    }

    public double processOrder(String card, double total) {
        if(userInfo.containsKey(card)) {
            if(total > userInfo.get(card)) {
                return ThreadLocalRandom.current().nextInt(2,101); // Success 
            } else {
                return 1; // Insufficient funds
            }
        }
        return 0; // Unknown card
    }

}