/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.net.*;
import java.io.*;
import java.util.Scanner;
/**
 *
 * @author Megan
 */
public class SocketServer {
    public static void main(String[] args) throws IOException {
        //setting the port for communication
        Scanner scanner = new Scanner(System.in);
        int portNumber = scanner.nextInt();

        try (
                ServerSocket serverSocket = new ServerSocket(portNumber);
                Socket clientSocket = serverSocket.accept();
                PrintWriter out =
                        new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));
        ) {

            String inputLine, outputLine;

            // Initiate conversation with client
            ServerProtocol serverProtocol = new ServerProtocol();
            outputLine = serverProtocol.processInput(null);
            out.println(outputLine);

            while ((inputLine = in.readLine()) != null) {
                if (inputLine.length() > 0){
                    outputLine = serverProtocol.processInput(inputLine);
                    out.println(outputLine);
                    if (outputLine.equals("Successful communication"))
                        break;
                }
            }
//            out.close();
//            in.close();
//            clientSocket.close();
//            serverSocket.close();

        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                    + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}
