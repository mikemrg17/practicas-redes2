
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws Exception {
        //Código del cllente
        // Scanner scanner = new Scanner( System.in );
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader( System.in ) );
    
        System.out.print("Escribe la dirección del servidor: ");
        String host = bufferedReader.readLine();
    
        System.out.print("Escribe el puerto del servidor: ");
        int port = Integer.parseInt(bufferedReader.readLine());
    
        for(;;) {
    
            Socket socket = new Socket(host, port);
            ObjectOperator objectOperator = new ObjectOperator();
    
            Catalog catalog = ( Catalog ) objectOperator.readObject(socket.getInputStream());


            Client.receiveCatalogImagesFromServer(socket);




            // catalog.clearCatalog();
            objectOperator.writeObject(catalog, socket.getOutputStream());

            socket.close();
    
            System.out.print("¿Quieres realizar otra compra? (Escribe N o n para no): ");
            char continueFlag = bufferedReader.readLine().charAt(0);
    
            if( continueFlag != 0 && ( continueFlag == 'N' || continueFlag == 'n') ) break;
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

}