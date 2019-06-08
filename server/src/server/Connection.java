package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;

public class Connection extends Thread {

    private Socket client;
    private DataInputStream input;
    private DataOutputStream output;

    public Connection(Socket client) {
        try {
            this.client = client;
            this.input = new DataInputStream(client.getInputStream());
            this.output = new DataOutputStream(client.getOutputStream());
            this.start();
        } catch(IOException ex){
            
        }
    }

    @Override
    public void run() {
        try {
            //le do fluxo de entrada
            String data = input.readUTF();
            //escrevre no fluxo de saida
            output.writeUTF("tamanho " + data.length());
            System.out.println("Reply sent");
        } catch (EOFException e) {
            System.out.println("Conexao: EOFException " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Conexao: IOException " + e.getMessage());
        } finally {
            try {
                client.close();
            } catch (IOException e) {
                System.out.println("Conexao: erro close do socket");
            }
        }
    }
}
