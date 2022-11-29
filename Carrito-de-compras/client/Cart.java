import java.util.HashMap;

public class Cart {
    public static HashMap<Integer, Integer> cart = new HashMap<Integer, Integer>();

    public Cart() {
        cart.clear();
    }

    public Cart getCart(){
        return this;
    }

    public void printCart() {
        System.out.println(cart);
    }

    public void addProduct(int id, int quantity){
        cart.put(id, quantity);
    }

    public void removeProduct(int id){
        cart.remove(id);
    }

    public void updateQuantity(int id, int quantity){
        cart.replace(id, quantity);
    }
}