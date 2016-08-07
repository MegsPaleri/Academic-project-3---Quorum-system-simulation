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
import java.sql.*;

public interface ReceiveMessageInterface extends Remote{
  void receiveVersion(String x, String y) throws RemoteException,SQLException;
  String receiveVersion1(String x,String y) throws RemoteException,SQLException;
  String selectrecords(String x) throws RemoteException,SQLException;
  int updaterecords(String rollno,String[] array) throws RemoteException,SQLException;
  void display(String[] ar)throws RemoteException;
  int writenewrecord(int vno,String sid,String sname,String saddress,int phone) throws RemoteException,SQLException;
}