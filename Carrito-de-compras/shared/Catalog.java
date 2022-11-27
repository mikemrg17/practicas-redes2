import java.util.ArrayList;
import java.io.Serializable;

public class Catalog implements Serializable {
    private ArrayList<Item> catalog = new ArrayList<Item>();    

    public Catalog() {
        catalog.clear();
    }
}