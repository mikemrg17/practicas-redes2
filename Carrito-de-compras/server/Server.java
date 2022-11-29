import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static final int port = 3014;

    public static void main(String[] args) {
        try{

            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Servidor iniciado, en espera de conexiones en el puerto " + port);

            for(;;){
                Catalog catalog = Server.getCatalagFromFile();

                Socket clientSocket = serverSocket.accept();
                System.out.println("Conexión establecida desde " + clientSocket.getInetAddress() + ":" + clientSocket.getPort());
                ObjectOperator objectOperator = new ObjectOperator();

                objectOperator.writeObject(catalog, clientSocket.getOutputStream());

                
                Server.sendCatalogImagesToClient( catalog, clientSocket );


                catalog = (Catalog) objectOperator.readObject( clientSocket.getInputStream() );
                Server.saveCatalogToFile(catalog);
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static Catalog getCatalagFromFile() {

        Catalog catalog = null;

        try {

            FileInputStream fileInputStream = new FileInputStream("./db.obj");
            ObjectOperator objectOperator = new ObjectOperator();

            catalog = ( Catalog ) objectOperator.readObject(fileInputStream);

            fileInputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return catalog;
    }

    public static void saveCatalogToFile( Catalog catalog ) {

        try {

            FileOutputStream fileOutputStream = new FileOutputStream("./db.obj");
            ObjectOperator objectOperator = new ObjectOperator();

            objectOperator.writeObject( catalog , fileOutputStream);

            fileOutputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void sendCatalogImagesToClient( Catalog catalog, Socket socket ) {

        String imagePaths[] = catalog.getItemsImagePaths();

        try {
            
            DataOutputStream dataOutputStream = new DataOutputStream( socket.getOutputStream() );

            dataOutputStream.writeInt( imagePaths.length );
            dataOutputStream.flush();

            DataInputStream dataInputStream;

            for(int i = 0; i < imagePaths.length; i++){

                File file = new File( "./images/" + imagePaths[i] );
                
                String filePath = file.getAbsolutePath();
                String fileName = file.getName();
                long fileSize = file.length();

                dataInputStream = new DataInputStream( new FileInputStream( filePath ) );
  
                dataOutputStream.writeUTF( fileName );
                dataOutputStream.flush();
  
                dataOutputStream.writeLong( fileSize );
                dataOutputStream.flush();
  
                byte[] buffer = new byte[1024];
                long sent = 0;
                int bytesRead;
  
                while( sent < fileSize ) {
  
                    bytesRead = dataInputStream.read( buffer );
                    dataOutputStream.write( buffer, 0, bytesRead );
                    dataOutputStream.flush();
  
                    sent += bytesRead;
                }

                dataInputStream.close();

            }

            dataOutputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}