import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Catalog implements Serializable {
    private ArrayList<Item> catalog = new ArrayList<Item>();    
    
    public Catalog() {
    }

    public void fillCatalog() {
        catalog.add( new Item( "Sudadera", "Sudadera de algodon lavable", new String[] {"Rojo", "Negro", "Azul"}, 15, "sudadera.jpg", "M", 450f, 15 ) );
        catalog.add( new Item( "Camisa", "Camisa de lino lavable", new String[] {"Rojo", "Negro", "Azul"}, 15, "sudadera.jpg", "M", 450f, 15 ) );
        catalog.add( new Item( "Pantalon", "Pnatalon chinoe", new String[] {"Rojo", "Negro", "Azul"}, 15, "sudadera.jpg", "M", 450f, 15 ) );
    }

    public void clearCatalog(){
        this.catalog.clear();
    }

    public ArrayList<Item> getCatalog(){
        return this.catalog;
    } 

    public void saveCatalog() throws FileNotFoundException, IOException {

        ObjectOutputStream objectOutputStream = new ObjectOutputStream( new FileOutputStream( "./recibido/db.obj" ) );
        objectOutputStream.writeObject(this);
        objectOutputStream.flush();
        objectOutputStream.close();

    }

        
}