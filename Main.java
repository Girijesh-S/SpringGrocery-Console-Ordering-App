import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static List<Category> catalog = new ArrayList<>();
    static List<OrderItem> cart = new ArrayList<>();
    static UserDetails user = null;
    static final List<String> allowedCities = Arrays.asList("Chennai", "Chengalpattu", "Kancheepuram");
    static final List<Double> qtyOptions = Arrays.asList(0.25, 0.5, 1.0);

    public static void main(String[] args) {
        initCatalog();
        System.out.println("Welcome to SpringGrocery-Organic Store");
        System.out.println("Good Day, Please place your Order and Enter your Details for Home Delivery");
        System.out.println("Free home delivery on above Rs.500");
        System.out.println("Currently functional only in TamilNadu - Chennai, Chengalpattu, Kancheepuram");
        mainMenu();
    }

    static void mainMenu() {
        while(true) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Enter User Details");
            System.out.println("2. View Products");
            System.out.println("3. View Bill and Delivery Details");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            String input = scanner.nextLine();

            switch(input) {
                case "1": enterUserDetails(); break;
                case "2":
                    if(user == null) System.out.println("Please enter user details first (option 1)");
                    else viewCategories();
                    break;
                case "3":
                    if(cart.isEmpty()) System.out.println("No items ordered yet.");
                    else if(user == null) System.out.println("Please enter user details first (option 1)");
                    else displayBillAndDetails();
                    break;
                case "4":
                    System.out.println("Thank you! Exiting the SpringGrocery. Come back soon.");
                    System.exit(0);
                    break;
                default: System.out.println("Invalid choice, please try again.");
            }
        }
    }

    static void enterUserDetails() {
        UserDetails tempUser = new UserDetails();

        System.out.print("Enter your Name: ");
        tempUser.name = scanner.nextLine();

        while(true) {
            System.out.print("Enter your Phone Number (10 digits): ");
            tempUser.phone = scanner.nextLine();
            if(tempUser.phone.length() == 10) break;
            System.out.println("Invalid phone number. Please enter 10 digits.");
        }

        while(true) {
            System.out.print("Enter Alternative Phone Number (10 digits): ");
            tempUser.altPhone = scanner.nextLine();
            if(tempUser.altPhone.length() == 10) break;
            System.out.println("Invalid phone number. Please enter 10 digits.");
        }

        while(true) {
            System.out.print("Enter Pincode (6 digits): ");
            tempUser.pincode = scanner.nextLine();
            if(tempUser.pincode.length() == 6) break;
            System.out.println("Invalid pincode. Please enter 6 digits.");
        }

        tempUser.state = "TamilNadu";

        System.out.println("Choose your City:");
        for(int i = 0; i < allowedCities.size(); i++) {
            System.out.println((i+1) + ". " + allowedCities.get(i));
        }
        System.out.print("Enter choice (1-" + allowedCities.size() + "): ");
        int cityChoice;
        while(true) {
            try {
                cityChoice = Integer.parseInt(scanner.nextLine());
                if(cityChoice >= 1 && cityChoice <= allowedCities.size()) {
                    tempUser.city = allowedCities.get(cityChoice-1);
                    break;
                }
                System.out.println("Invalid choice. Please enter between 1 and " + allowedCities.size());
            } catch(Exception e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }

        System.out.print("Enter Address Line 1: ");
        tempUser.addressLine1 = scanner.nextLine();

        System.out.print("Enter Address Line 2: ");
        tempUser.addressLine2 = scanner.nextLine();

        System.out.println("Choose Delivery Date:");
        System.out.println("1. Today");
        System.out.println("2. Tomorrow");
        //System.out.println("3. Day After Tomorrow");
        String dateChoice = scanner.nextLine();
        switch(dateChoice) {
            case "1": tempUser.deliveryDate = "Today"; break;
            case "2": tempUser.deliveryDate = "Tomorrow"; break;
            //case "3": tempUser.deliveryDate = "Day After Tomorrow"; break;
            default: 
                System.out.println("Invalid choice, Setting delivery date to Today.");
                tempUser.deliveryDate = "Today";
        }

        user = tempUser;
        System.out.println("User details saved successfully.");
    }

    static void viewCategories() {
        while(true) {
            System.out.println("\nProduct Categories:");
            for(int i = 0; i < catalog.size(); i++) {
                System.out.println((i+1) + ". " + catalog.get(i).name);
            }
            System.out.println("-1. Back to Main Menu");
            System.out.print("Choose category: ");
            String input = scanner.nextLine();

            if(input.equals("-1")) return;

            int choice;
            try {
                choice = Integer.parseInt(input);
            } catch(Exception e) {
                System.out.println("Invalid input");
                continue;
            }

            if(choice < 1 || choice > catalog.size()) {
                System.out.println("Invalid choice");
                continue;
            }

            viewProducts(catalog.get(choice - 1));
        }
    }

    static void viewProducts(Category category) {
        while(true) {
            System.out.println("\n" + category.name + " Products:");
            for(int i = 0; i < category.items.size(); i++) {
                System.out.println((i+1) + ". " + category.items.get(i));
            }
            System.out.println("-1. Back to Categories");
            System.out.print("Choose product: ");
            String input = scanner.nextLine();

            if(input.equals("-1")) return;

            int choice;
            try {
                choice = Integer.parseInt(input);
            } catch(Exception e) {
                System.out.println("Invalid input");
                continue;
            }

            if(choice < 1 || choice > category.items.size()) {
                System.out.println("Invalid choice");
                continue;
            }

            productMenu(category.items.get(choice-1));
        }
    }

    static void productMenu(Item item) {
        while(true) {
            System.out.println("\nProduct: " + item.name);
            System.out.println("Available: " + item.availableStock + " " + item.unit);
            System.out.println("Price: Rs." + item.pricePerUnit + " per " + item.unit);
            System.out.println("Choose Quantity:");
            
            List<Double> quantities = item.allowedQuantities;
            for(int i = 0; i < quantities.size(); i++) {
                System.out.println((i+1) + ". " + quantities.get(i) + " " + item.unit);
            }
            System.out.println((quantities.size()+1) + ". Back to Products");
            
            System.out.print("Choose option: ");
            String input = scanner.nextLine();

            try {
                int choice = Integer.parseInt(input);
                if(choice == quantities.size()+1) return;
                if(choice >= 1 && choice <= quantities.size()) {
                    double qty = quantities.get(choice-1);
                    if(qty > item.availableStock) {
                        System.out.println("Sorry only " + item.availableStock + " " + item.unit + " available.");
                        continue;
                    }
                    item.availableStock -= qty;
                    cart.add(new OrderItem(item, qty));
                    System.out.println("Added to cart: " + qty + " " + item.unit + " of " + item.name);
                } else {
                    System.out.println("Invalid choice");
                }
            } catch(Exception e) {
                System.out.println("Invalid input");
            }
        }
    }

    static void displayBillAndDetails() {
        System.out.println("\n--- Order Bill ---");
        double totalPrice = 0;
        for(OrderItem oi : cart) {
            System.out.println(oi);
            totalPrice += oi.getTotalPrice();
        }
        System.out.printf("Total Price: Rs.%.2f\n", totalPrice);

        if(totalPrice >= 500) {
            System.out.println("Free Delivery");
        } else {
            System.out.println("Delivery Charge: Rs.100");
            totalPrice += 100;
        }
        System.out.printf("Amount to Pay: Rs.%.2f\n", totalPrice);

        System.out.println("\n--- Delivery Details ---");
        System.out.println(user);
    }

    static void initCatalog() {
        List<Double> qtyKg = Arrays.asList(0.25, 0.5, 1.0);
        List<Double> qtyLitre = Arrays.asList(0.25, 0.5, 1.0);

        // Fruits
        Category fruits = new Category("Fruits");
        fruits.items.add(new Item("Banana - Yelakki", "kg", 10, qtyKg, 90));
        fruits.items.add(new Item("Banana - Red Banana", "kg", 10, qtyKg, 100));
        fruits.items.add(new Item("Mango - Banganapalli", "kg", 10, qtyKg, 60));
        fruits.items.add(new Item("Papaya", "kg", 10, qtyKg, 30));
        fruits.items.add(new Item("Guava", "kg", 10, qtyKg, 70));
        catalog.add(fruits);

        // Vegetables
        Category vegetables = new Category("Vegetables");
        vegetables.items.add(new Item("Onion", "kg", 10, qtyKg, 30));
        vegetables.items.add(new Item("Tomato", "kg", 10, qtyKg, 25));
        vegetables.items.add(new Item("Potato", "kg", 10, qtyKg, 40));
        vegetables.items.add(new Item("Carrot", "kg", 10, qtyKg, 70));
        vegetables.items.add(new Item("Brinjal", "kg", 10, qtyKg, 50));
        catalog.add(vegetables);

        // Rice & Cereals - Single Category
        Category riceCereals = new Category("Rice & Cereals");
        riceCereals.items.add(new Item("Basmati Rice", "kg", 10, qtyKg, 120));
        riceCereals.items.add(new Item("Idli Rice", "kg", 10, qtyKg, 50));
        riceCereals.items.add(new Item("Ragi", "kg", 10, qtyKg, 70));
        riceCereals.items.add(new Item("Wheat", "kg", 10, qtyKg, 35));
        catalog.add(riceCereals);

        // Oils & Ghee
        Category oilsGhee = new Category("Oils & Ghee");
        oilsGhee.items.add(new Item("Sunflower Oil", "litre", 10, qtyLitre, 180));
        oilsGhee.items.add(new Item("Coconut Oil", "litre", 10, qtyLitre, 250));
        oilsGhee.items.add(new Item("Ghee", "litre", 10, qtyLitre, 300));
        catalog.add(oilsGhee);
    }
}