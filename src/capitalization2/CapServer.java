/* 
Project: L01: Solo Work - A Capitalization Server
Purpose Details: Set up Server/Client app that echoes text and capitalizes
Course: IST411
Author: Sam Janvey
Date Developed: 1/15/2020
Last Date Changed: 1/15/2020
Revision: 
*/
package capitalization2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class CapServer {
    public static void main(String[] args) {
        
        // Print Title to the Console
        System.out.println("Capitalization Server");

        // Attempt to initiate server by listening on port 6000
        try (ServerSocket serverSocket = new ServerSocket(6000)) {
            System.out.println("Waiting for connection......");
            
            // Instantiate Socket object for client when incoming connection is made
            Socket clientSocket = serverSocket.accept();
            System.out.println("Connected to client");
            
            // Attempt to establish I/O data channel for data exchange
            try (BufferedReader br = new BufferedReader(new InputStreamReader(
                    clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                String inputLine;
                
                // Forever loop will run as long as data exists
                // Input will be converted to Upper Case
                while ((inputLine = br.readLine().toUpperCase()) != null) {
                    
                    // Echo back the input text from client
                    System.out.println("Server: " + inputLine);
                    
                    // Send text back to client
                    out.println(inputLine);
                }
            }        
        }
        // In case something goes wrong
        // Print error message
        catch (IOException ex) {
            System.out.println("Oops, something went wrong.");
            System.out.println("Try Again");
        }
        
    }
}
