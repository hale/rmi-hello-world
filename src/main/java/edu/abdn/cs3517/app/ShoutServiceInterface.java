package edu.abdn.cs3517.app;

import java.rmi.*;

/**
 * The remote interface for a simple warehouse.
 *
 */
public interface ShoutServiceInterface extends Remote
{
  public String shout(String s) throws RemoteException;
}
