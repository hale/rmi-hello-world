package edu.abdn.cs3517.app;

import java.rmi.*;
import java.rmi.server.*;
import java.net.*;
import edu.abdn.cs3517.app.ShoutServiceInterface;

public class ShoutServerMainline
{
  public static void main (String[] args) {
    if (args.length < 2) {
      System.out.println( "Usage:\njava ShoutServerMainline ");
      return;
    }

    try {
      String hostname = (InetAddress.getLocalHost()).getCanonicalHostName();
      int registryport = Integer.parseInt( args[0] );
      int serviceport = Integer.parseInt( args[1] );
      System.setProperty( "java.security.policy", "rmishout.policy");
      //System.setProperty"java.rmi.server.codebase", "file:classes/");
      System.setSecurityManager( new RMISecurityManager() );
      ShoutService shoutservice = new ShoutService();
      ShoutServiceInterface shoutstub = (ShoutServiceInterface)UnicastRemoteObject.exportObject( shoutservice, serviceport );
      String regURL = "rmi://" + hostname + ":" + registryport + "/ShoutService";
      System.out.println( "Registering " + regURL);
      Naming.rebind( regURL, shoutstub );

      // NB the server will not shut down.

    }
    catch(java.net.UnknownHostException e) {
      System.err.println("Cannot get local host name." );
      System.err.println( e.getMessage() );
    }
    catch (java.io.IOException e) {
      System.err.println( "Failed to register.");
      System.err.println( e.getMessage());
    }
  }
}


