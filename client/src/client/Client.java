package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    public static void main(String[] args) {
        Socket socket = null;
        try {
            //associa socket a uma porta e conecta com o servidor remoto
            socket = new Socket("localhost", 8181);
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            //escreve no fluxo de saida
            output.writeUTF("mensagem teste");
            //le do fluxo de entrada
            String response = input.readUTF();
            System.out.println("Success: " + response);
        } catch (UnknownHostException ex) {
            System.out.println("UnknownHostException: " + ex.getMessage());
        } catch (IOException ex) {
           System.out.println("IOException: " + ex.getMessage());
        } finally{
          if (socket != null)
                try {
                    socket.close();
                } catch (IOException ex) {
                    System.out.println("Falha: " + ex.getMessage());
                }  
        }
    }

}
