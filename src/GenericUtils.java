import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GenericUtils <T extends Product>{
public static <T> void printList(List<T> list){
        for(T t : list){
            System.out.println(t.toString());
        }
        
    }
    public static <T extends Comparable<T>> T getMax(List<T> list){
        T max = list.get(0);
        for(T t : list){
            if(t.compareTo(max) > 0){
                max = t;
            }
        }
        return max;
    }
     Map<Integer,Customer> customers = new HashMap<>();
     long nextCustomerId;
     Map<Integer,T> products = new HashMap<>();
     long nextProductId;
    public void addCustomer(Customer customer) {
        nextCustomerId = customer.getId();
        customers.put((int)nextCustomerId, customer);
    }
    public Customer getCustomerById(long  id) {
        return customers.values().stream()
                        .filter(c -> c.getId().equals(id))
                        .findFirst()
                        .orElse(null);
    }
     public boolean isCustomerExists(Long id) {
        return customers.values().stream()
        .anyMatch(c -> c.getId()
        .equals(id));
    }
    public void addProduct(T t){
        nextProductId = t.getId();
        products.put((int)nextProductId, t);
    } 
    public Product getProductById(long id){
        return products.values().stream()
        .filter(t -> t.getId().equals(id))
        .findFirst()
        .orElse(null);
    }
    public boolean abilityToSubtract(Product product, int quantity){
        if(product.getQuantity() >= quantity){
            return true;

        }
        else{
            return false;
        }

    }
    public void substractProductQuantity(Product product, int quantity){
        int res = product.getQuantity() - quantity;
        product.setQuantity(res);
        
    }
    public List<T> getProductsSortedByPrice(){
        return products.values().stream().sorted((a,b) -> Float.compare(a.getPrice(), b.getPrice()))
        .collect(Collectors.toList());
    }
    public List<T> getProductsByCategory(String category){
        return products.values().stream()
        .filter( p -> p.getCategory().equalsIgnoreCase(category))
        .collect(Collectors.toList());
    }
    public void deleteProduct(long id){
        products.remove((int)id);
    }
}
