/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package quorumreplication;

/**
 *
 * @author SHAHNA
 */
import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;
import java.net.*;
import java.sql.*;
import java.io.*;

public class RmiReadServer1 extends
  java.rmi.server.UnicastRemoteObject implements ReceiveMessageInterface{

  String address;
  Registry registry, registry2, registry3, registry4, registry5;
 // RMIFromServer rmiserver2;
  static String x=null,y=null,z=null;
int[] ports={3232,1099,3234,3235,3236};



  public String receiveVersion1(String x,String y) throws RemoteException,SQLException
  {
      return ""+1;
  }

  public void receiveVersion(String rollno,String y) throws RemoteException,SQLException{
       String vno2=null,vno3=null,vno4=null,vno5=null;
       String s=null;
       int flag=0;
       System.out.println("To sort1:"+s+"\t"+vno2+"\t"+vno3+"\t"+vno4+"\t"+vno5);
      try
  {

          //System.out.println("hi-recv");
          int vno=0;
      Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
      Connection con=DriverManager.getConnection("jdbc:odbc:quorumdsn","usr","pwd");

      if(y.equals("yes"))
      {
       try{
           String serverAddress="127.0.0.1";
           String serverPort="1099";
           ReceiveMessageInterface rmiServer;
           String serverPort2="3234";
           String serverPort3="3235";
           String serverPort4="3236";

  registry=LocateRegistry.getRegistry
  (serverAddress,(new Integer(serverPort)).intValue());
  rmiServer=(ReceiveMessageInterface)(registry.lookup("rmiServer2"));
  // call the remote method
    vno2=rmiServer.receiveVersion1(rollno,"no");
   System.out.println("from server 2 : "+vno2);
   Thread.sleep(1000);
    registry2=LocateRegistry.getRegistry
  (serverAddress,(new Integer(serverPort2)).intValue());
  rmiServer=(ReceiveMessageInterface)(registry2.lookup("rmiServer3"));
  // call the remote method
    vno3=rmiServer.receiveVersion1(rollno,"no");
   System.out.println("from server 3: "+vno3);
   Thread.sleep(1000);
    registry3=LocateRegistry.getRegistry
  (serverAddress,(new Integer(serverPort3)).intValue());
  rmiServer=(ReceiveMessageInterface)(registry3.lookup("rmiServer4"));
  // call the remote method
    vno4=rmiServer.receiveVersion1(rollno,"no");
   System.out.println("from server 4: "+vno4);
   Thread.sleep(1000);
    registry4=LocateRegistry.getRegistry
  (serverAddress,(new Integer(serverPort4)).intValue());
  rmiServer=(ReceiveMessageInterface)(registry4.lookup("rmiServer5"));
  // call the remote method
    vno5=rmiServer.receiveVersion1(rollno,"no");
   System.out.println("from server 5: "+vno5);



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
       s="1,"+vno;
      System.out.println("from server 1 : "+s);


int i=0;
int n=5;
int k=0;
     int[] a=new int[5];
     int[] b=new int[5];
     int[] sid=new int[5];




  System.out.println("THE VERSION NUMBERS :"+s+"\t"+vno2+"\t"+vno3+"\t"+vno4+"\t"+vno5);


      //to sort

int s1=s.indexOf(",");
String s11=s.substring(0,s1);
int a1=Integer.parseInt(s11);
String s12=s.substring(s1+1);
int b1=Integer.parseInt(s12);



int s2=vno2.indexOf(",");
String s21=vno2.substring(0,s2);
int a2=Integer.parseInt(s21);
String s22=vno2.substring(s2+1);
int b2=Integer.parseInt(s22);

int s3=vno3.indexOf(",");
String s31=vno3.substring(0,s3);
int a3=Integer.parseInt(s31);
String s32=vno3.substring(s3+1);
int b3=Integer.parseInt(s32);

int s4=vno2.indexOf(",");
String s41=vno4.substring(0,s4);
int a4=Integer.parseInt(s41);
String s42=vno4.substring(s4+1);
int b4=Integer.parseInt(s42);

int s5=vno5.indexOf(",");
String s51=vno5.substring(0,s5);
int a5=Integer.parseInt(s51);

String s52=vno5.substring(s5+1);
int b5=Integer.parseInt(s52);

a[0]=a1;
a[1]=a2;
a[2]=a3;
a[3]=a4;
a[4]=a5;
b[0]=b1;
b[1]=b2;
b[2]=b3;
b[3]=b4;
b[4]=b5;
System.out.println("THE SERVER ID :");
for(i=0;i<5;i++)
{
    System.out.println(a[i]+"\t");
}
System.out.println("THE VERSION NUMBERS :");
for(i=0;i<5;i++)
{
    System.out.println(b[i]+"\t");
}

for(i=0;i<n;i++)
{
    for(int j=0;j<n-1;j++)
    {
        if(b[i]>b[j])
        {
            int temp1=b[i];
            b[i]=b[j];
            b[j]=temp1;

            int temp2=a[i];
            a[i]=a[j];
            a[j]=temp2;
        }
    }

}
System.out.println("The Sorted Version Numbers :");
for(i=0;i<n;i++)
{
    System.out.println(b[i]+"\t");

}
System.out.println("The greatest Vesrion Number with ID: ID"+a[0]+"\t VNO"+b[0]);
int cnt=0;
int grt=b[0];
int getfrom=a[0];
for(i=0;i<n;i++)
{
    if(b[i]==grt)
    {
        cnt++;
        //System.out.println(cnt);

       // System.out.println("server id:"+a[i]);
        
    }
    else
    {
        sid[k]=a[i];
        //System.out.println("not satisfying ids"+sid[k]);

        k++;
    }
}
for(int m=0;m<=k;m++)
{
    if(sid[m]==1)
        {
            flag=1;
            break;
            
        }
}
if(cnt==n)
{
    String record=null;
   
    System.out.println("\nAll have the same and latest version number, so no need to update replicas");
    try
    {
    Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
    Connection con1=DriverManager.getConnection("jdbc:odbc:quorumdsn","usr","pwd");
    PreparedStatement ps1=con.prepareStatement("select * from student_records where st_id=?");
    ps1.setString(1, rollno);
    System.out.println("Rollno value ="+rollno);
    ResultSet rs1=ps1.executeQuery();
    while(rs1.next())
    {
       record=""+rs1.getInt(1);
       record+="|"+rs1.getString(2);
       record+="|"+rs1.getString(3);
       record+="|"+rs1.getString(4);
       record+="|"+rs1.getInt(5)+"||";
    }
    String re=record;
  
           int index=re.indexOf("|");
           String[] manza=new String[6];
          
           manza[0]=re.substring(0,index);
          
           int q=1;
           re=re.substring(index+1);
       
           while(!re.equals("||"))
           {
               index=re.indexOf("|");
            
               manza[q]=re.substring(0,index);
              
               q++;
               re=re.substring(index+1);
               if(q>5)
               {
                   break;

               }

           }
           ReceiveMessageInterface rmiClient;
  //Registry registry;
  String serverAddress1="127.0.0.1";
  String serverPort1="3233";

  //String text="hello";
    try{
  registry=LocateRegistry.getRegistry
  (serverAddress1,(new Integer(serverPort1)).intValue());
  rmiClient=(ReceiveMessageInterface)(registry.lookup("rmiClient"));
  // call the remote method
  rmiClient.display(manza);
  }
  catch(RemoteException e){
  e.printStackTrace();
  }

  catch(NotBoundException e){
  System.err.println(e);
  }

   // return record;
    }
    catch(Exception e)
    {

    }
}
else if(cnt>=n/2)
        {
            System.out.println("\nRead Possible and have to update remaining replicas");
            int serverPort=ports[getfrom-1];
            String serverAddress="127.0.0.1";
            ReceiveMessageInterface rmiServer;
            registry=LocateRegistry.getRegistry(serverAddress,serverPort);
           rmiServer=(ReceiveMessageInterface)(registry.lookup("rmiselect"));
           String re=rmiServer.selectrecords(rollno);
           int index=re.indexOf("|");
           String[] manza=new String[6];
        
           manza[0]=re.substring(0,index);
        
           int q=1;
           re=re.substring(index+1);
      
           while(!re.equals("||"))
           {
               index=re.indexOf("|");
             
               manza[q]=re.substring(0,index);
          
               q++;
               re=re.substring(index+1);
               if(q>5)
               {
                   break;
               
               }

           }
      

           for(i=0;i<k;i++)
           {
               System.out.println("\nServer ID  which have to update with latest version number :"+sid[i]);
           }
i=0;
   // for(i=0;i<=k;i++){
           while(i<=k){
       //serverAddress="127.0.0.1";
        System.out.println("Server ID is Updating ="+sid[i]);
      if(sid[i]==2)
      {
          serverPort=1099;
      }
      else if(sid[i]==3)
      {
          serverPort=3234;
      }
      else if(sid[i]==4)
      {
          serverPort=3235;

      }
      else if(sid[i]==5)
      {
          serverPort=3236;
      }
  registry=LocateRegistry.getRegistry(serverAddress,serverPort);
   System.out.println("port "+serverAddress+""+serverPort);
   System.out.println("version no ="+sid[i]);
  rmiServer=(ReceiveMessageInterface)(registry.lookup("rmiupdate"));
  // call the remote method
    int res=rmiServer.updaterecords(rollno,manza);
    for(int l=0;l<5;l++)
    {
    System.out.println(" LATEST RECORD : "+manza[l]);
    }
   //System.out.println("from server 2 : "+vno2);
   Thread.sleep(1000);
  i++;
    }
ReceiveMessageInterface rmiClient;
  //Registry registry;
  String serverAddress1="127.0.0.1";
  String serverPort1="3233";

  //String text="hello";
    try{
  registry=LocateRegistry.getRegistry
  (serverAddress1,(new Integer(serverPort1)).intValue());
  rmiClient=(ReceiveMessageInterface)(registry.lookup("rmiClient"));
  // call the remote method
  rmiClient.display(manza);
  }
  catch(RemoteException e){
  e.printStackTrace();
  }

  catch(NotBoundException e){
  System.err.println(e);
  }

//for server1
   if(flag==1)
   {
      
        try
      {
          System.out.println("updated tabl 1");
          Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
          Connection con1=DriverManager.getConnection("jdbc:odbc:quorumdsn","usr","pwd");
         PreparedStatement ps1=con1.prepareStatement("update student_records set version_no=?, st_name=?, st_address=?,phone_no=? where st_id=?");
        //  System.out.println("version no array2"+a);
            int as=Integer.parseInt(manza[0]);
          ps1.setInt(1,as);
          ps1.setString(2,manza[2]);
          ps1.setString(3,manza[3]);
          ps1.setInt(4,Integer.parseInt(manza[4]));
          ps1.setString(5,rollno);
          int p1=ps1.executeUpdate();
System.out.println("\tWrite successfully in server1 [self table]");
      }
      catch(Exception e)
      {

      }
   }

   
}
        
else
{
    System.out.println("\nRead is not possible and have to update all replicas ");
/*
    int serverPort=ports[getfrom-1];
            String serverAddress="127.0.0.1";
            ReceiveMessageInterface rmiServer;
            registry=LocateRegistry.getRegistry(serverAddress,serverPort);
           rmiServer=(ReceiveMessageInterface)(registry.lookup("rmiselect"));
           String re=rmiServer.selectrecords(rollno);
           int index=re.indexOf("|");
           String[] manza=new String[6];
       
           manza[0]=re.substring(0,index);
         
           int q=1;
           re=re.substring(index+1);
           
           while(!re.equals("||"))
           {
               index=re.indexOf("|");
          
               manza[q]=re.substring(0,index);
         
               q++;
               re=re.substring(index+1);
               if(q>5)
               {
                   break;

               }

           }

   // int serverPort=ports[getfrom-1];
   // String serverAddress="127.0.0.1";
   // ReceiveMessageInterface rmiServer;

    i=0;
   // for(i=0;i<=k;i++){
           while(i<=k){
       //serverAddress="127.0.0.1";
        System.out.println("\nServer ID to Update with latest version number : "+sid[i]);
      if(sid[i]==2)
      {
          serverPort=1099;
      }
      else if(sid[i]==3)
      {
          serverPort=3234;
      }
      else if(sid[i]==4)
      {
          serverPort=3235;

      }
      else if(sid[i]==5)
      {
          serverPort=3236;
      }
  registry=LocateRegistry.getRegistry(serverAddress,serverPort);
   System.out.println("port "+serverAddress+""+serverPort);
   System.out.println("\nServerID is being Updating .. :"+sid[i]);
  rmiServer=(ReceiveMessageInterface)(registry.lookup("rmiupdate"));
  // call the remote method
    int res=rmiServer.updaterecords(rollno,manza);
    for(int l=0;l<5;l++)
    {
    System.out.println("Latest Record : "+manza[l]);
    }
   //System.out.println("from server 2 : "+vno2);
   Thread.sleep(1000);
  i++;
    }
ReceiveMessageInterface rmiClient;
  //Registry registry;
  String serverAddress1="127.0.0.1";
  String serverPort1="3233";

  //String text="hello";
    try{
  registry=LocateRegistry.getRegistry
  (serverAddress1,(new Integer(serverPort1)).intValue());
  rmiClient=(ReceiveMessageInterface)(registry.lookup("rmiClient"));
  // call the remote method
  rmiClient.display(manza);
  }
  catch(RemoteException e){
  e.printStackTrace();
  }

  catch(NotBoundException e){
  System.err.println(e);
  }

//for server1
   if(flag==1)
   {
      
        try
      {
          System.out.println("updated tabl 1");
          Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
          Connection con1=DriverManager.getConnection("jdbc:odbc:quorumdsn","usr","pwd");
         PreparedStatement ps1=con1.prepareStatement("update student_records set version_no=?, st_name=?, st_address=?,phone_no=? where st_id=?");
        //  System.out.println("version no array2"+a);
            int as=Integer.parseInt(manza[0]);
          ps1.setInt(1,as);
          ps1.setString(2,manza[2]);
          ps1.setString(3,manza[3]);
          ps1.setInt(4,Integer.parseInt(manza[4]));
          ps1.setString(5,rollno);
          int p1=ps1.executeUpdate();
System.out.println("\nWrite successfully in server1 [self table]");
      }
      catch(Exception e)
      {

      }
   }

*/


}
     }
     catch(Exception e)
     {
      System.out.println(e);
     }


  }

public void display(String[] ar)throws RemoteException
{
    
}

public String selectrecords(String x) throws RemoteException,SQLException
{
    String record=null;
    try
    {
    Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
    Connection con=DriverManager.getConnection("jdbc:odbc:quorumdsn","usr","pwd");
    PreparedStatement ps=con.prepareStatement("select * from student_records where st_id=?");
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

public int updaterecords(String rno,String[] array) throws RemoteException,SQLException
{
    return 0;
     

}
/*public String display(String[] ar) throws RemoteException,SQLException
{

}
*/
  public int writenewrecord(int vno, String sid, String sname, String saddress, int phone) throws RemoteException,SQLException
  {
      try
      {
          String serverAddress="127.0.0.1";    //IP of the communicating machine (Client m/c)
           String serverPort="1099";            //port no. between server 1 and 2
           ReceiveMessageInterface rmiServer;
           String serverPort2="3234";           //port no. between server 1 and 3
            String serverPort3="3235";          //port no. between server 1 and 4
            String serverPort4="3236";          //port no. betweeen server 1 and 5

            registry2=LocateRegistry.getRegistry(serverAddress,(new Integer(serverPort).intValue()));
            rmiServer=(ReceiveMessageInterface)(registry2.lookup("rminewwrite"));
            int p1=rmiServer.writenewrecord(vno, sid, sname, saddress, phone);
            if(p1==1)
            {
                System.out.println("Write successfull in server2");
            }


            registry3=LocateRegistry.getRegistry(serverAddress,(new Integer(serverPort2).intValue()));
            rmiServer=(ReceiveMessageInterface)(registry3.lookup("rminewwrite"));
           p1=rmiServer.writenewrecord(vno, sid, sname, saddress, phone);
            if(p1==1)
            {
                System.out.println("Write successfull in server3");
            }

            registry4=LocateRegistry.getRegistry(serverAddress,(new Integer(serverPort3).intValue()));
            rmiServer=(ReceiveMessageInterface)(registry4.lookup("rminewwrite"));
           p1=rmiServer.writenewrecord(vno, sid, sname, saddress, phone);
            if(p1==1)
            {
                System.out.println("Write successfull in server4");
            }

            registry5=LocateRegistry.getRegistry(serverAddress,(new Integer(serverPort4).intValue()));
            rmiServer=(ReceiveMessageInterface)(registry5.lookup("rminewwrite"));
            p1=rmiServer.writenewrecord(vno, sid, sname, saddress, phone);
            if(p1==1)
            {
                System.out.println("Write successfull in server5");
            }


          Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
          Connection con=DriverManager.getConnection("jdbc:odbc:quorumdsn","usr","pwd");
          PreparedStatement ps=con.prepareStatement("insert into student_records values(?,?,?,?,?)");
          ps.setInt(1,vno);
          ps.setString(2,sid);
          ps.setString(3,sname);
          ps.setString(4,saddress);
          ps.setInt(5,phone);
          int p2=ps.executeUpdate();
          if(p1==1&&p2==1)
            {
                return p2;
            }
          else
          {
              return 0;
          }
      }
      catch(Exception e)
      {
          System.out.println(e);
      }
      return 0;
  }

  public String[] searchrecord(String rollno) throws RemoteException, SQLException
  {
      int vno=0;
      try
  {
     Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
      Connection con=DriverManager.getConnection("jdbc:odbc:quorumdsn","usr","pwd");
      PreparedStatement ps=con.prepareStatement("select version_no from student_records where st_id=?");
      ps.setString(1,rollno);
      ResultSet rs=ps.executeQuery();
      while(rs.next())
      {
          vno=rs.getInt(1);
      }

      }
      catch(Exception ex)
      {

      }
      return null;
  }
  public RmiReadServer1() throws RemoteException{
  try{
  address = (InetAddress.getLocalHost()).toString();
  }
  catch(Exception e){
  System.out.println("can't get inet address.");
  }
  int port=3232;
  System.out.println("this address=" + address +  ",port=" + port);
  try{
  registry = LocateRegistry.createRegistry(port);
  System.out.println("hi-registry");
  registry.rebind("rmiServer", this);
  registry.rebind("rmiselect",this);
  registry.rebind("rmiupdate",this);
  registry.rebind("rminewwrite",this);
  registry.rebind("rmioldupdate", this);
  }
  catch(RemoteException e){
  System.out.println("remote exception"+ e);
  }
  }

  public static void main(String args[]) throws RemoteException{

  RmiReadServer1 server = new RmiReadServer1();


  }
}
