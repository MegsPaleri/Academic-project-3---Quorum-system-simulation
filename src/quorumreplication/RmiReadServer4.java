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
import java.rmi.server.*;
import java.net.*;
import java.sql.*;
import java.io.*;
public class RmiReadServer4 extends
  java.rmi.server.UnicastRemoteObject implements ReceiveMessageInterface{

  String address;
  Registry registry,registry1;
 // RMIFromServer rmiserver2;
  static String x=null,y=null,z=null;
  Connection con=null;



  public String receiveVersion1(String x,String y) throws RemoteException,SQLException
  {
      int vno=0;
      try{

      Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
      con=DriverManager.getConnection("jdbc:odbc:quorumdsn","usr","pwd");
      PreparedStatement ps=con.prepareStatement("select version_no from student_records4 where st_id=?");
      ps.setString(1,x);
      ResultSet rs=ps.executeQuery();

      while(rs.next())
      {
          vno=rs.getInt(1);
      }
      }
      catch(Exception e)
      {

      }
      String s="4,"+vno;
      return s;
  }

  public void receiveVersion(String rollno,String y) throws RemoteException,SQLException{
      try
  {
          //System.out.println("hi-recv");
          int vno;

        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
      con=DriverManager.getConnection("jdbc:odbc:quorumdsn","usr","pwd");
      if(y.equals("yes"))
      {
       try{
           String serverAddress="127.0.0.1";
           String serverPort="1099";
           ReceiveMessageInterface rmiServer;

  registry=LocateRegistry.getRegistry
  (serverAddress,(new Integer(serverPort)).intValue());
  rmiServer=(ReceiveMessageInterface)(registry.lookup("rmiServer"));
  // call the remote method
  rmiServer.receiveVersion(rollno,"no");
  }
  catch(RemoteException e){
  e.printStackTrace();
  }
  catch(NotBoundException e){
  System.err.println(e);
  }
      }

      PreparedStatement ps=con.prepareStatement("select version_no from student_records where st_id=?");
      ps.setString(1,rollno);
      ResultSet rs=ps.executeQuery();

      while(rs.next())
      {
          vno=rs.getInt(1);
      }
     }
     catch(Exception e)
     {
      System.out.println(e);
     }

         // Registry registry;
      //String serverAddress="169.254.235.250";
      String serverAddress="127.0.0.1";
      String serverPort="3234";
     // int serverPort=3232;
     

  try{
      
      registry=LocateRegistry.getRegistry(serverAddress,(new Integer(serverPort)).intValue());
   
     // rmiserver2=(RMIFromServer)(registry.lookup("rmiServer2"));

  
  RmiReadServer1 server = new RmiReadServer1();
  }
  catch (Exception e){
  e.printStackTrace();
  System.exit(1);
  }
       //rmiserver2.displayMessage(x,y,z);


  }
 public String selectrecords(String x) throws RemoteException,SQLException
{
    String record=null;
    try
    {
    Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
    Connection con=DriverManager.getConnection("jdbc:odbc:quorumdsn","usr","pwd");
    PreparedStatement ps=con.prepareStatement("select * from student_records4 where st_id=?");
    ps.setString(1, x);
    ResultSet rs=ps.executeQuery();
    while(rs.next())
    {
       record=""+rs.getInt(1);
       record+="|"+rs.getString(2);
       record+="|"+rs.getString(3);
       record+="|"+rs.getString(4);
       record+="|"+rs.getInt(5)+"||";
    }
    return record;
    }
    catch(Exception e)
    {

    }
    return record;
}
 public void display(String[] ar)throws RemoteException
{

}
   public int updaterecords(String rno,String[] array)throws RemoteException,SQLException
  {
      try
      {
          for(int i=0;i<5;i++)
    {
    System.out.println("Record To update : "+array[i]);
    }
       
        
        //  System.out.println("version no array"+a);
          
          Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
          Connection con=DriverManager.getConnection("jdbc:odbc:quorumdsn","usr","pwd");
          PreparedStatement ps=con.prepareStatement("update student_records4 set version_no=?, st_name=?, st_address=?,phone_no=? where st_id=?");
        //  System.out.println("version no array2"+a);
            int a=Integer.parseInt(array[0]);
          ps.setInt(1,a);
          ps.setString(2,array[2]);
          ps.setString(3,array[3]);
           ps.setInt(4,Integer.parseInt(array[4]));

          ps.setString(5,rno);
          int p=ps.executeUpdate();
          System.out.println("\nTable4 updated");
          if(p==1)
              return 1;
          else
              return 0;

      }
      catch(Exception e)
      {

      }
      return 0;
  }

     public int writenewrecord(int vno,String sid, String sname, String saddress, int phone) throws RemoteException,SQLException
  {
      try
      {
           Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
          Connection con=DriverManager.getConnection("jdbc:odbc:quorumdsn","usr","pwd");
          PreparedStatement ps=con.prepareStatement("insert into student_records4 values(?,?,?,?,?)");
          ps.setInt(1,vno);
          ps.setString(2,sid);
          ps.setString(3,sname);
          ps.setString(4,saddress);
          ps.setInt(5,phone);
          int p2=ps.executeUpdate();
          return p2;
      }
      catch(Exception e)
      {

      }
      return 0;
  }

  public RmiReadServer4() throws RemoteException,SQLException{

  try{

  address = (InetAddress.getLocalHost()).toString();
  }
  catch(Exception e){
  System.out.println("can't get inet address.");
  }
  int port=3235;
  System.out.println("this address=" + address +  ",port=" + port);
  try{
  registry = LocateRegistry.createRegistry(port);
  System.out.println("hi-registry");
  registry.rebind("rmiServer4", this);
  registry.rebind("rmiselect",this);
  registry.rebind("rmiupdate",this);
  registry.rebind("rminewwrite",this);
  }
  catch(RemoteException e){
  System.out.println("remote exception"+ e);
  }
  }
  public static void main(String args[]) throws RemoteException,SQLException{

    
  RmiReadServer4 server = new RmiReadServer4();

  }
}
