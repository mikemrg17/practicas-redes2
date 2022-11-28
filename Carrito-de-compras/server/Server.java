import java.net.ServerSocket;
import java.net.Socket;
import shared.Catalog;

public class Server {

    private static final int port = 7000;

    public static void main(String[] args) {
        try{
            ServerSocket serverSocket = new ServerSocket(port);
            Catalog catalog = new Catalog();

            while(true){
                System.out.println("Escuchando en el puerto " + port);
                Socket clientSocket = serverSocket.accept();
                System.out.println("Conexión establecida desde " + clientSocket.getInetAddress() + ":" + clientSocket.getPort());

                //Envío del catalogo serializado
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}