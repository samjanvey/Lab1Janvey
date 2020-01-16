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
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class CapClient {
    
    public static void main(String[] args) {
        
        // Print title to console
        System.out.println("Capitalization Client");
        
        // Attempt to obtain local host address
        try {
            System.out.println("Waiting for connection......");
            InetAddress localAddress = InetAddress.getLocalHost();
            
            // Attempt to connect to server on port 6000, establish channel for data exchange
            try (Socket clientSocket = new Socket(localAddress, 6000);
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                
                // Success
                System.out.println("Connected to server");
                
                // Get input from console
                Scanner scanner = new Scanner(System.in);
                
                // Ask user for text input
                // Continue prompting until user types "quit"
                while(true) {
                    System.out.println("Enter text: ");
                    String inputLine = scanner.nextLine();
                    if("quit".equalsIgnoreCase(inputLine)) {
                        break;
                    }
                    
                    // Send text to server
                    out.println(inputLine);
                    
                    // Receive response from server and print to console
                    String response = br.readLine();
                    System.out.println("Server response: " + response);
                }
            }
        } 
        // Error Handling
        catch (IOException ex) {
            System.out.println("Oops, something went wrong.");
            System.out.println("Try Again");
        }
    }
}
