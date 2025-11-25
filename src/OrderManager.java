
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*OrderManager
використовує Map<Integer, Order<?>> для зберігання замовлень (ключ — ID замовлення);
методи:
addOrder(Order<?> order) — додати замовлення;
getOrderById(int id) — знайти замовлення;
getOrdersByCustomer(String name) — знайти всі замовлення клієнта;
getMostValuableOrder() — знайти замовлення з найбільшою сумою.
GenericUtils — клас із універсальними (generic) методами, наприклад:
public static <T> void printList(List<T> list) — вивід будь-якого списку;
public static <T extends Comparable<T>> T getMax(List<T> list) — пошук максимального елемента. */
public class OrderManager<T extends Product> {
    private Map<Integer, Order<T>> orders = new HashMap<>();
    private long nextMapId;
    public OrderManager(){

    }
    public void addOrder(Order<T> order){
        nextMapId = order.getId();
        orders.put((int)nextMapId,order);
    }
    public Order<T> getOrderById(int id){   
        return orders.get(id);
    }
    public List<Order<T>> getOrdersByCustomer(String name){
        return orders.values().stream()
        .filter(order -> order.getCustomer().getName().equalsIgnoreCase(name))
        .collect(Collectors.toList());
    }
    public void printOrders(){
       for( Map.Entry<Integer, Order<T>> o : orders.entrySet()){
        System.out.println("mapKey=" + o.getKey() + ", " + o.getValue());
        }
    }
    public Order<T> getMostValuableOrder(){
        List<Order<T>> orderList = new ArrayList<>(orders.values());
        return GenericUtils.getMax(orderList);
    }
    public List<Order<T>> getAllOrders(){
        List<Order<T>> res = orders.values().stream().collect(Collectors.toList());
        return res;  
    }
    public boolean isOrderExists(Long id){
        boolean res = orders.values().stream().anyMatch( o -> o.getId().equals(id));
        return res;
    }
    public List<Order<T>> getOrdersSortedByPrice(){
        return orders.values().stream()
        .sorted().collect(Collectors.toList());
    }
}
