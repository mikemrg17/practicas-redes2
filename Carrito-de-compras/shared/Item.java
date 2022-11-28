import java.io.Serializable;

public class Item implements Serializable {
    //Atributos serializables : distintas imágenes del producto, descripción,  colores  disponibles,  tamaño,  precio,  tiempo  de  entrega,  etc.
    private String name = null;
    private String description = null;
    private String[] colors;
    private int stock = 0;
    private String imagePath = null;
    private String size = null;
    private float price = 0.0f;
    private int shippingTime = 0;

    //Métodos
    public Item() {

    }

    public Item(String name, String description, String[] colors, int stock, String imagePath, String size, float price, int shippingTime){
        this.name = name;
        this.description = description;
        this.colors = colors;
        this.stock = stock;
        this.imagePath = imagePath;
        this.size = size;
        this.price = price;
        this.shippingTime = shippingTime;
    }


}