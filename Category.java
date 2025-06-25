import java.util.ArrayList;
import java.util.List;

public class Category {
    public String name;
    public List<Item> items = new ArrayList<>();

    public Category(String name) {
        this.name = name;
    }
}