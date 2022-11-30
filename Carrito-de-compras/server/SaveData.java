import java.io.FileOutputStream;

public class SaveData {
  
  public static void main( String []args ) {


    Catalog catalog = new Catalog();

    catalog.addItem( new Item(0, "Sudadera", "Sudadera de algodon lavable", new String[] {"Rojo", "Negro", "Azul"}, 15, "sudadera.jpeg", "M", 450f, 15 ) );
    catalog.addItem( new Item(1, "Camisa", "Camisa de lino lavable", new String[] {"Rojo", "Negro", "Azul"}, 15, "camisa.jpeg", "M", 200f, 4 ) );
    catalog.addItem( new Item(2, "Pantalon", "Pantalon chino", new String[] {"Rojo", "Negro", "Azul"}, 15, "pantalon.jpg", "M", 930f, 31 ) );

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
