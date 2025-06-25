import java.util.List;

public class Item {
    public String name;
    public String unit;
    public double availableStock;
    public List<Double> allowedQuantities;
    public double pricePerUnit;

    public Item(String name, String unit, double availableStock, 
               List<Double> allowedQuantities, double pricePerUnit) {
        this.name = name;
        this.unit = unit;
        this.availableStock = availableStock;
        this.allowedQuantities = allowedQuantities;
        this.pricePerUnit = pricePerUnit;
    }

    @Override
    public String toString() {
        return name; 
        // Just Checking the Pull Request Changes.
    }
}