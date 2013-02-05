package edu.abdn.cs3517.app;

import java.rmi.*;

public class ShoutService implements ShoutServiceInterface
{
  public ShoutService() throws RemoteException
  { }

  public String shout( String s ) throws RemoteException
  {
    return s.toUpperCase();
  }
}
