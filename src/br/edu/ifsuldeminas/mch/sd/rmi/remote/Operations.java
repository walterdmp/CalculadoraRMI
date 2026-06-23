package br.edu.ifsuldeminas.mch.sd.rmi.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Operations extends Remote {
    Number sum(Number x, Number y) throws RemoteException;
    Number sub(Number x, Number y) throws RemoteException;
    Number mul(Number x, Number y) throws RemoteException;
    Number div(Number x, Number y) throws RemoteException;
    
    Number root(Number value, Number index) throws RemoteException; 
    Number power(Number base, Number exponent) throws RemoteException; 
    Number percentage(Number value, Number total) throws RemoteException; 
    Number modulo(Number x, Number y) throws RemoteException; 
    
    long factorial(int n) throws RemoteException; 
    String decToBin(int dec) throws RemoteException;
    String decToHex(int dec) throws RemoteException;
    
    List<String> lastOperations(int howMany) throws RemoteException;
    List<String> lastOperations() throws RemoteException;
}