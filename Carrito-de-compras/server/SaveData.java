import java.io.FileOutputStream;

public class SaveData {
  
  public static void main( String []args ) {
    Catalog catalog = new Catalog();

    Item item1 = new Item(0, "Sudadera", "Sudadera de algodon lavable", new String[] {"Rojo", "Negro", "Azul"}, 15, "sudadera.jpeg", "M", 450f, 15 );
    Item item2 = new Item(1, "Camisa", "Camisa de lino lavable", new String[] {"Rojo", "Negro", "Azul"}, 15, "camisa.jpeg", "M", 200f, 4 );
    Item item3 = new Item(2, "Pantalon", "Pantalon chino", new String[] {"Rojo", "Negro", "Azul"}, 15, "pantalon.jpg", "M", 930f, 31 );

    Item.items.add( item1 );
    Item.items.add( item2 );
    Item.items.add( item3 );
    
    catalog.addItem( item1 );
    catalog.addItem( item2 );
    catalog.addItem( item3 );

    try {
      
      FileOutputStream fileOutputStream = new FileOutputStream( "./db.obj" );

      ObjectOperator objectOperator = new ObjectOperator();
      objectOperator.writeObject(catalog, fileOutputStream);

      fileOutputStream.close();

    } catch (Exception e) {
      e.printStackTrace();
    }

  }

}
