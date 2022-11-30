import java.io.Serializable;

public class Item implements Serializable {
    //Atributos serializables : distintas imágenes del producto, descripción,  colores  disponibles,  tamaño,  precio,  tiempo  de  entrega,  etc.
    private int id = 0;
    private String name = null;
    private String description = null;
    private String[] colors;
    private int stock = 0;
    private String imagePath = null;
    private String size = null;
    private float price = 0.0f;
    private int shippingTime = 0;

    public Item(int id, String name, String description, String[] colors, int stock, String imagePath, String size, float price, int shippingTime){
        this.id = id;
        this.name = name;
        this.description = description;
        this.colors = colors;
        this.stock = stock;
        this.imagePath = imagePath;
        this.size = size;
        this.price = price;
        this.shippingTime = shippingTime;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getColors() {
        return this.colors;
    }

    public void setColors(String[] colors) {
        this.colors = colors;
    }

    public int getStock() {
        return this.stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getImagePath() {
        return this.imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getSize() {
        return this.size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public float getPrice() {
        return this.price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getShippingTime() {
        return this.shippingTime;
    }

    public void setShippingTime(int shippingTime) {
        this.shippingTime = shippingTime;
    }

     public void printItem() {
        System.out.println("Id: " + this.id);
        System.out.println("\tProducto: " + this.name);
        System.out.println("\tDescripción: " + this.description);
        System.out.println("\tDisponibles: " + this.stock);
        System.out.println("\tTamaño: " + this.size);
        System.out.println("\tPrecio: " + this.price);
        System.out.println("\tLlega en: " + this.shippingTime + " días");
        System.out.println("\n");
    }



}