package br.edu.ifsuldeminas.mch.sd.rmi.server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import br.edu.ifsuldeminas.mch.sd.rmi.remote.Operations;

public class Server {
    private static final int RMI_DEFAULT_PORT = 1099;

    public Server() {
        try {
            Calculator calculator = new Calculator();
            
            Registry registry = LocateRegistry.createRegistry(RMI_DEFAULT_PORT);
            
            Operations stub = (Operations) UnicastRemoteObject.exportObject(calculator, RMI_DEFAULT_PORT);
            
            registry.rebind("CalculatorService", stub);
            
            System.out.println("[INFO] Servidor RMI da Calculadora iniciado com sucesso.");
            System.out.println("[INFO] Aguardando conexões na porta " + RMI_DEFAULT_PORT + "...");
            
        } catch (Exception e) {
            System.err.println("[ERRO] Falha crítica ao inicializar o servidor RMI.");
            System.err.println("[ERRO] Detalhe: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        System.out.println("[INFO] Inicializando sistema...");
        new Server();
    }
}