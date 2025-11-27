
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class main {
    public static void main(String[] args) throws Exception {
        GenericUtils genericUtils = new GenericUtils();
        Scanner scanner = new Scanner(System.in);
        OrderManager orderManager = new OrderManager<>();
        Customer customer1 = new Customer(1L, "Vlad" , "vlad@gmail.com" , "0669785288");
        genericUtils.addCustomer(customer1);
        Product product1 = new Product(1L, "Phone", "Iphone 7", 9999, 100);
        genericUtils.addProduct(product1);
        Order order1 = new Order<>(1L, LocalDate.now(), customer1, new ArrayList<>());
        orderManager.addOrder(order1);
    System.out.println("Welcome to online shop VNU"); 
      label: while(true){
            System.out.println("Commands: ");
            System.out.println("1 - create new order");
            System.out.println("2 - create new customer");
            System.out.println("3 - login as admin");
            System.out.println("4 - show products sorted by price");
            System.out.println("5 - find products by category");
            System.out.println("0 - exit");
            int operation = scanner.nextInt();
            switch (operation){
                case 1:
                    System.out.println("enter an id");
                    Long id = scanner.nextLong();
                    if(orderManager.isOrderExists(id)){
                        System.out.println("this id is already taken");
                        continue;
                    }
                    LocalDate localDate = LocalDate.now();
                    System.out.println("enter customer id");
                    Long customerId = scanner.nextLong();
                    Customer customer = genericUtils.getCustomerById(customerId) ;
                    if(customer== null){
                        System.out.println("no customer with that id");
                        continue;
                    }
                    boolean addMore = true;
                    List<Product> boughtProducts = new ArrayList<>();
                    while(addMore){
                        System.out.println("enter product id u want to buy");
                        Long productId = scanner.nextLong();
                        Product product = genericUtils.getProductById(productId);
                        if(product == null){
                            System.out.println("no product with that id");
                            continue;
                        }
                        System.out.println("U can buy" + product.getQuantity() +" How much u want to buy?");
                        int quantity = scanner.nextInt();
                        if(genericUtils.abilityToSubtract(product, quantity)){
                            genericUtils.substractProductQuantity(product, quantity);
                        }
                        else{
                            System.out.println("This quantity is not available.");
                        }
                        Product productToSave = new Product(
                        product.getId(),
                        product.getCategory(),
                        product.getName(),
                        product.getPrice(),
                        quantity 
                        );

                        boughtProducts.add(productToSave);
                        System.out.println("add more products? y/n");
                        String answer = scanner.next();
                        if(answer.equalsIgnoreCase("n")){
                        addMore = false;
    }
                    }
                    Order order = new Order(id, localDate, customer, boughtProducts);
                    orderManager.addOrder(order);
                    System.out.println("Succesfull");
                    break;
                case 2:
                    System.out.println("enter new customer id");
                     Long newCustomerId = scanner.nextLong();
                     scanner.nextLine();
                     if(genericUtils.isCustomerExists(newCustomerId) == true){
                        System.out.println("there already is customer with that id");
                        continue;
                     }
                     System.out.println("enter customer name");
                     String name = scanner.nextLine();
                     System.out.println("enter customer email");
                     String email = scanner.nextLine();
                     System.out.println("enter customer phone number");
                     String phone = scanner.nextLine();
                     Customer newCustomer = new Customer(newCustomerId, name, email, phone);
                     genericUtils.addCustomer(newCustomer);
                     System.out.println("Succesfull");
                     break;
                case 3:
                    Boolean adminBool = true;
                    admin: while(adminBool){
                        System.out.println("Commands: ");
                        System.out.println("1 - show all orders");
                        System.out.println("2 - add product");
                        System.out.println("3 - find order by Id");
                        System.out.println("4 - find user by id");
                        System.out.println("5 - find product by id");
                        System.out.println("6 - get most valuable order");
                        System.out.println("7 - get sorted by value orders");
                        System.out.println("8 - delete product");
                        System.out.println("0 - back to main menu");
                        int adminOperation = scanner.nextInt();
                        switch(adminOperation){
                            case 1:
                                orderManager.printOrders();
                            break;
                            case 2:
                                System.out.println("enter product id");
                                Long productId = scanner.nextLong();
                                if(genericUtils.getProductById(productId) != null){
                                    System.out.println("there already is product with that id");
                                    continue;
                                }
                                scanner.nextLine();
                                System.out.println("enter product category");
                                String category = scanner.nextLine();
                                System.out.println("enter product name");
                                String productName = scanner.nextLine();
                                System.out.println("enter product price");
                                Float price = scanner.nextFloat();
                                System.out.println("enter product quantity");
                                int quantity = scanner.nextInt();
                                Product product = new Product(productId, category, productName, price, quantity);
                                genericUtils.addProduct(product);
                                System.out.println("Succesfull");
                                break;
                            case 3: 
                            System.out.println("enter order id");
                            int orderId = scanner.nextInt();
                            Order foundOrder = orderManager.getOrderById(orderId);
                            if(foundOrder ==  null){
                                System.out.println("There is no order with that id");
                                
                            }
                            else{
                                System.out.println(foundOrder.toString());                          
                            }
                            break;
                            case 4:
                                System.out.println("enter user id");
                                long userId = scanner.nextLong();
                                Customer foundUser = genericUtils.getCustomerById(userId);
                            if(foundUser ==  null){
                                System.out.println("There is no user with that id");
                                
                            }
                            else{
                                System.out.println(foundUser.toString());                          
                            }
                            break;
                            case 5:
                                  System.out.println("Enter product id");
                                  long productToFindId = scanner.nextLong();
                                  Product foundProduct = genericUtils.getProductById(productToFindId);
                                   if(foundProduct ==  null){
                                System.out.println("There is no product with that id");
                                
                            }
                            else{
                                System.out.println(foundProduct.toString());                          
                            }
                            break;
                            case 6:
                                System.out.println(orderManager.getMostValuableOrder());
                                break;
                            case 7:
                                List<Order> sortedOrders = orderManager.getOrdersSortedByPrice();
                                for(Order p : sortedOrders){
                                    System.out.println(p.toString());
                                }
                                break;
                            case 8: 
                                System.out.println("Enter product id");
                                long productIdToDelete = scanner.nextLong();
                                genericUtils.deleteProduct(productIdToDelete);
                                System.out.println("Succesful");
                                break;
                            case 0: 
                            break admin;
                        }
                            
                    }
                    break;
                    case 4:
                        List<Product> sortedProducts = genericUtils.getProductsSortedByPrice();
                        for(Product p : sortedProducts){
                            System.out.println(p.toString());
                        }
                        break;
                    case 5:
                        scanner.nextLine();
                        System.out.println("Enter category");
                        String categoryToFind = scanner.nextLine();
                        List<Product> foundByCategory = genericUtils.getProductsByCategory(categoryToFind);
                        if(foundByCategory.isEmpty()){
                            System.out.println("There is no products found by that category");
                        }
                        else{
                            for(Product p : foundByCategory){
                                System.out.println(p.toString());
                            }
                        }
                    break;
            }

        }
    }
}
