/*методи: toString().
Order<T extends Product> — узагальнений клас, що містить:
номер замовлення, дату, клієнта, список товарів (List<T>),
методи для підрахунку загальної вартості та додавання товарів. */
import java.time.LocalDate;
import java.util.List;
public class Order<T extends Product> implements Comparable<Order<T>> {
    private Long id;
    private LocalDate date;
    private Customer customer;
    private List<T> productList;
    public Order(){
    }
    public Order(Long id, LocalDate date, Customer customer, List<T> productList) {
        this.id = id;
        this.date = date;
        this.customer = customer;
        this.productList = productList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<T> getProductList() {
        return productList;
    }

    public void setProductList(List<T> productList) {
        this.productList = productList;
    }
    public Float totalCost(){
        float sum = 0;
        for(Product product : productList){
            sum += product.getPrice() * product.getQuantity();
        }
        return sum;
    }
    public void addProduct(T t){
        productList.add(t);
    }
    @Override
    public int compareTo(Order<T> other) {
        return Double.compare(this.totalCost(), other.totalCost());
    }
    @Override
    public String toString() {
    return  "id=" + id +
            ", date=" + date +
            ", customer=" + (customer != null ? customer.getName() : "null") +
            ", products=" + productList +
            ", totalPrice=" + totalCost() +
            '}';
    }
}
