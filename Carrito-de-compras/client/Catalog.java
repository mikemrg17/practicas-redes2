import java.util.ArrayList;
import java.util.Iterator;
import java.io.Serializable;

public class Catalog implements Serializable {
    private ArrayList<Item> catalog = new ArrayList<Item>();    
    
    public Catalog() {
    }

    public void clearCatalog(){
        this.catalog.clear();
    }

    public ArrayList<Item> getCatalog(){
        return this.catalog;
    }

    public void setCatalog(ArrayList<Item> catalog){
        this.catalog = catalog;
    }

    public void addItem( Item item ) {
        this.catalog.add(item);
    }

    public String[] getItemsImagePaths() {

        String paths[] = new String[ this.catalog.size() ]; 

        Iterator<Item> iterator = this.catalog.iterator();
        
        Item item = null;
        int i = 0;

        while( iterator.hasNext() ) {

            item = ( Item ) iterator.next();
            paths[i] = item.getImagePath();
            i++;
        }

        return paths;
    }

        public void printCatalog() {
        Iterator<Item> iterator = this.catalog.iterator();

        while( iterator.hasNext() ) {
            Item item = ( Item ) iterator.next();
            item.printItem();
        }
    }
        
}