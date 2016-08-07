/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package quorumreplication;

/**
 *
 * @author Megha Paleri
 */
import java.rmi.*;
import java.rmi.registry.*;
import java.net.*;
import java.io.*;
import java.sql.*;

 public class RmiClient1 extends
  java.rmi.server.UnicastRemoteObject implements ReceiveMessageInterface{
String address;
Registry registry;

public int writenewrecord(int vno,String sid,String sname,String saddress,int phone) throws RemoteException,SQLException
{
    return 0;
}
public void receiveVersion(String x,String y) throws RemoteException,SQLException
{
}
public String receiveVersion1(String x,String y) throws RemoteException,SQLException
{
    return null;
}
  public String selectrecords(String x) throws RemoteException,SQLException
  {
  return null;
  }
  public int updaterecords(String rollno,String[] array) throws RemoteException,SQLException
  {
  return 0;
  }

public void display(String[] array) throws RemoteException
{
System.out.println("version="+array[0]);
System.out.println("id="+array[1]);
System.out.println("name="+array[2]);
System.out.println("address="+array[3]);
System.out.println("phone="+array[4]);

}

public RmiClient1() throws RemoteException{
try{
  address = (InetAddress.getLocalHost()).toString();
  }
  catch(Exception e){
  System.out.println("can't get inet address.");
  }
  int port=3233;
  System.out.println("this address=" + address +  ",port=" + port);
  try{
  registry = LocateRegistry.createRegistry(port);
  registry.rebind("rmiClient", this);
  }
  catch(RemoteException e){
  System.out.println("remote exception"+ e);
  }
  }
  static public void main(String args[])throws IOException,SQLException{
try
{
RmiClient1 client=new RmiClient1();
}
catch (Exception e){
  e.printStackTrace();
  System.exit(1);
  }
String s=null;
try
{
BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
s=br.readLine();
}
catch(IOException e)
{
}
//String text=s;
//String rollno=s;
  ReceiveMessageInterface rmiServer;
  Registry registry;
  String serverAddress="127.0.0.1";
  String serverPort="3232";
  /*System.out.println
   ("sending " + text + " to " +serverAddress + ":" + serverPort);
  try{

  registry=LocateRegistry.getRegistry
  (serverAddress,(new Integer(serverPort)).intValue());
  rmiServer=(ReceiveMessageInterface)(registry.lookup("rmiServer"));
  // call the remote method
  rmiServer.receiveVersion(rollno, "yes");
 }
  catch(RemoteException e){
  e.printStackTrace();
  }
catch(SQLException e){
  System.err.println("this is error"+e);
  }
  catch(NotBoundException e){
  System.err.println(e);
  }*/
  //String s=null;
  BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
  BufferedReader br1=new BufferedReader(new InputStreamReader(System.in));
  System.out.println("What do you want to do?");
  System.out.println("1.New Write (nw) 2.Read (r)");
  String option=br1.readLine();
  if(option.equals("r"))
  {
   System.out.println("Enter student roll number");
  String rollno=br.readLine();
  String text=rollno;
  System.out.println
   ("sending " + text + " to " +serverAddress + ":" + serverPort);
  try{
  registry=LocateRegistry.getRegistry
  (serverAddress,(new Integer(serverPort)).intValue());
  rmiServer=(ReceiveMessageInterface)(registry.lookup("rmiServer"));
  // call the remote method
  rmiServer.receiveVersion(rollno, "yes");
  }
  catch(RemoteException e){
  e.printStackTrace();
  }
  catch(NotBoundException e){
  System.err.println(e);
  }
  }
  else if(option.equals("nw"))
  {
      System.out.println("Writing new student information");
      System.out.println("---------------------------------");
      System.out.println("Enter new student details");
      int vno=1;
      String sid=br.readLine();
      String sname=br.readLine();
      String saddress=br.readLine();
      int phone=Integer.parseInt(br.readLine());
      try{
          registry=LocateRegistry.getRegistry(serverAddress,(new Integer(serverPort)).intValue());
          rmiServer=(ReceiveMessageInterface)(registry.lookup("rminewwrite"));
          int a=rmiServer.writenewrecord(vno, sid, sname, saddress, phone);
          if(a==1)
          {
              System.out.println("New write operation to all replicas successfull");
          }
          else if(a==0)
          {
              System.out.println("New write operation not successfull");
          }
      }
      catch(RemoteException e)
      {
          e.printStackTrace();
      }
      catch(NotBoundException e){
          System.err.println(e);
      }

  }
}
 }

