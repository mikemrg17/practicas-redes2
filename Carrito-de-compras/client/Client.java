import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {

    static BufferedReader bufferedReader = new BufferedReader( new InputStreamReader( System.in ) );


    public static void main(String[] args) throws Exception {
        //Código del cllente
        // Scanner scanner = new Scanner( System.in );
        int option = 0, secondOption = 0;
    
        System.out.print("Escribe la dirección del servidor: ");
        String host = bufferedReader.readLine();
    
        System.out.print("Escribe el puerto del servidor: ");
        int port = Integer.parseInt(bufferedReader.readLine());
    
        for(;;) {
    
            Socket socket = new Socket(host, port);
            ObjectOperator objectOperator = new ObjectOperator();
    
            Catalog catalog = ( Catalog ) objectOperator.readObject(socket.getInputStream());


            Client.receiveCatalogImagesFromServer(socket);

            Cart cart = new Cart();
            while(option != 7){
                clearScreen();
                System.out.println("¿Qué acción quieres realizar?");
                System.out.println("\t1. Consultar catálogo");
                System.out.println("\t2. Consultar carrito de compras");
                System.out.println("\t3. Agregar elemento al carrito");
                System.out.println("\t4. Eliminar elemeto del carrito");
                System.out.println("\t5. Cambiar cantidad de elemento del carrito");
                System.out.println("\t6. Realizar compra");
                System.out.println("\t7. Salir");
                option = Integer.parseInt(bufferedReader.readLine());
                switch(option){
                    case 1:
                        clearScreen();
                        catalog.printCatalog();
                        System.out.println("Salir -> 0");
                        secondOption = Integer.parseInt(bufferedReader.readLine());
                        if(secondOption == 0)
                            break;
                    
                    case 2:
                        clearScreen();
                        cart.printCart();
                        System.out.println("Salir -> 0");
                        secondOption = Integer.parseInt(bufferedReader.readLine());
                        if(secondOption == 0)
                            break;
                    
                    case 3:
                        clearScreen();
                        catalog.printCatalog();
                        System.out.println("Ingresa el id del producto que quieres agregar y cuantos");
                        int id = 0, quantity = 0;
                        System.out.println("Id: \t");
                        id = Integer.parseInt(bufferedReader.readLine());
                        System.out.println("Cantidad: \t");
                        quantity = Integer.parseInt(bufferedReader.readLine());
                        cart.addProduct(id, quantity);
                        break;
                    case 4:
                        clearScreen();
                        System.out.println("Ingresa el id del producto que quieres eliminar");
                        System.out.println("Id: \t");
                        id = Integer.parseInt(bufferedReader.readLine());
                        cart.removeProduct(id);
                        break;
                    case 5:
                        clearScreen();
                        catalog.printCatalog();
                        System.out.println("Ingresa el id del producto que quieres modificar y cuantos");
                        System.out.println("Id: \t");
                        id = Integer.parseInt(bufferedReader.readLine());
                        System.out.println("Nueva Cantidad: \t");
                        quantity = Integer.parseInt(bufferedReader.readLine());
                        cart.updateQuantity(id, quantity);
                        break;
                    
                    case 6:
                        clearScreen();
                        finalizePurchase(socket, catalog);
                        break;
                    case 7: break;
                    
                    default: break;
                }
            }


            // catalog.clearCatalog();
            objectOperator.writeObject(catalog, socket.getOutputStream());
    
            if(option == 7){
                socket.close();
                break;
            }
        }
    
        bufferedReader.close();

    }

    public static void receiveCatalogImagesFromServer( Socket socket ) {

        try {
            
            DataInputStream dataInputStream = new DataInputStream( socket.getInputStream() );

            int numberOfFiles = dataInputStream.readInt();

            for( int i = 0; i < numberOfFiles; i++ ){

                byte[] buffer = new byte[1024];
      
                String fileName = dataInputStream.readUTF();
                System.out.println( "Recibimos el archivo: " + fileName );
      
                long fileSize = dataInputStream.readLong();
                DataOutputStream dataOutputStream = new DataOutputStream( new FileOutputStream( "./images/" + fileName ) );
                
                long received = 0;
                int bytesRead;
      
                while( received < fileSize ) {
                  
                  bytesRead = dataInputStream.read( buffer, 0, (int)Math.min(fileSize - received, buffer.length) );
                
                  dataOutputStream.write( buffer, 0, bytesRead );
                  dataOutputStream.flush();
      
                  received += bytesRead;
      
                }

                dataOutputStream.close();
      
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void finalizePurchase(Socket socket, Catalog catalog) throws Exception{
        try {
            Document ticket = new Document();
            String ticketName = "ticket.pdf";
            PdfWriter.getInstance(ticket, new FileOutputStream(ticketName));
            ticket.open();
            Phrase header = new Phrase(" NOMBRE - PRECIO UNITARIO - CANTIDAD - PRECIO FINAL\n");
            ticket.add(header);
            float subtotal = 0.0f;

            Cart.cart.forEach( (id, quantity) -> {
                for(Item item: catalog.getCatalog()){
                    if(item.getId() == id)
                        item.setStock( item.getStock() - quantity );
                        Phrase body = new Phrase(item.getName() + " - " + item.getPrice() + " - " + quantity + " - " + item.getPrice()*quantity + "\n");
                        try{
                            ticket.add(body);
                        }catch(DocumentException e){
                            e.printStackTrace();
                        }
                        //subtotal += item.getPrice()*quantity;
                }
                //return subtotal;
            } );

            Phrase footer1 = new Phrase("\n\nSubtotal: " + subtotal + "\n");
            Phrase footer2 = new Phrase("\n\nTotal: " + subtotal*1.16);
            ticket.add(footer1);
            ticket.add(footer2);
            ticket.close();
            updateCatalog(catalog);
            int option = 0;
            System.out.println("Salir -> 0");
            option = Integer.parseInt(bufferedReader.readLine());
            if(option == 0){
                System.out.println("Compra finalizada");
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }

    public static void updateCatalog(Catalog catalog){
        try{
            FileOutputStream fileOutputStream = new FileOutputStream("db.obj");
            ObjectOperator objectOperator = new ObjectOperator();

            objectOperator.writeObject( catalog, fileOutputStream );
            fileOutputStream.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}