public class Product {
private Long id;
private String category;
private String name;
private float price;
private int quantity;
public Product(Long id, String category, String name, float price, int quantity){
    this.id = id;
    this.category = category;
    this.name = name;
    this.price = price;
    this.quantity = quantity;
}
public Product(){

}
public Long getId() {
    return id;
}

public void setId(Long id) {
    this.id = id;
}

public String getCategory() {
    return category;
}

public void setCategory(String category) {
    this.category = category;
}

public String getName() {
    return name;
}

public void setName(String name) {
    this.name = name;
}

public float getPrice() {
    return price;
}

public void setPrice(float price) {
    this.price = price;
}

public int getQuantity() {
    return quantity;
}

public void setQuantity(int quantity) {
    this.quantity = quantity;
}
@Override
public String toString(){
    return "name:" + name + ", category" + category +", name" + name + ", price" + price + ", quantity" + quantity ;
}
public int compareTo(Product other){
   return Float.compare(this.price, other.price);
}
}
