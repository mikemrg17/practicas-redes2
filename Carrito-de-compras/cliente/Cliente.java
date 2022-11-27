
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class Cliente {

    public static void main(String[] args) throws Exception {
        //Código del cliente

        // Scanner scanner = new Scanner( System.in );
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader( System.in ) );

        System.out.print("Escribe la dirección del servidor: ");
        String host = bufferedReader.readLine();

        System.out.print("Escribe el puerto del servidor: ");
        int port = Integer.parseInt(bufferedReader.readLine());

        for(;;) {

        

            Socket socket = new Socket(host, port);

            socket.close();


            System.out.print("¿Quieres realizar otra compra? (Escribe N o n para no): ");
            char continueFlag = bufferedReader.readLine().charAt(0);

            if( continueFlag != 0 && ( continueFlag == 'N' || continueFlag == 'n') ) break;
        }

        bufferedReader.close();

    }

}