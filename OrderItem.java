public class OrderItem {
    public Item item;
    public double quantityOrdered;

    public OrderItem(Item item, double quantityOrdered) {
        this.item = item;
        this.quantityOrdered = quantityOrdered;
    }

    public double getTotalPrice() {
        return quantityOrdered * item.pricePerUnit;
    }

    @Override
    public String toString() {
        return String.format("%s - %.2f %s @ Rs.%.2f = Rs.%.2f", 
            item.name, quantityOrdered, item.unit, item.pricePerUnit, getTotalPrice());
    }
}