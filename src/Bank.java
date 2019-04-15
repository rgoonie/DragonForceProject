import java.util.HashMap;

class Bank {

    private HashMap<String, Double> userInfo; // Hashmap is String: Card number double: Amount associated with card

    public Bank(HashMap<String, Double> userInfo) {
        this.userInfo = userInfo; 
    }

    public double processOrder(String card, double total) {
        if(userInfo.containsKey(card)) {
            if(total > userInfo.get(card)) {
                return 2; // Success 
            } else {
                return 1; // Insufficient funds
            }
        }
        return 0; // Unknown card
    }

}