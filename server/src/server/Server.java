package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Amanda
 */
public class Server {

    public static void main(String[] args) {
        int port = 8181;
        try {
            //cria socket associado a porta especificada para receber requisições
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("LISTENIG PORT: " + port);
            while (true) {
                //recupera requisições (bloqueante)
                Socket client = serverSocket.accept();
                System.out.println("Connection accepted " + client.getRemoteSocketAddress());
                //cria thread para se comunicar com o cliente
                new Connection(client);
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
