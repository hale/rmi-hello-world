package edu.abdn.cs3517.app;

import java.io.*;
import java.rmi.*;

public class ShoutClient
{
  public static void main (String[] args) {
    if (args.length < 2) {
      System.err.println( "Usage:\njava ShoutClient ");
      return;
    }
    String hostname = args[0];
    int registryport = Integer.parseInt(args[1]);
    System.setProperty( "java.security.policy", "rmishout.policy");
    System.setSecurityManager( new RMISecurityManager());

    try
    {
      String regURL = "rmi://" + hostname + ":" + registryport + "/ShoutService";
      System.out.println("Looking up " + regURL);
      ShoutServiceInterface shoutservice = (ShoutServiceInterface)Naming.lookup( regURL);

      // prompt user for a message
      BufferedReader in = new BufferedReader( new InputStreamReader( System.in ));
      System.out.print( "Enter Message: ");
      String message = in.readLine();

      String shoutResult = shoutservice.shout( message );
      System.out.println( shoutResult );
    }
    catch (java.io.IOException e) {
      System.err.println( "I/O error.");
      System.err.println( e.getMessage() );
    }
    catch (java.rmi.NotBoundException e) {
      System.err.println( "Server not bound");
      System.err.println( e.getMessage());
    }
  }
}
