package com.manning.nettyinaction.chapter1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * Listing 1.1 of <i>Netty in Action</i>
 *
 * @author <a href="mailto:norman.maurer@googlemail.com">Norman Maurer</a>
 */
public abstract class BlockingIoExample {

    public void serve(int portNumber) throws IOException {
        ServerSocket serverSocket = new ServerSocket(portNumber);       //1
        Socket clientSocket = serverSocket.accept();                    //2
        BufferedReader in = new BufferedReader(                         //3
                new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter out =
                new PrintWriter(clientSocket.getOutputStream(), true);
        String request, response;
        while ((request = in.readLine()) != null) {                     //4
            if ("Done".equals(request)) {                               //5
                break;
            }
        }
        response = processRequest(request);                             //6
        out.println(response);                                          //7
    }                                                                   //8

    protected abstract String processRequest(String request);
}
