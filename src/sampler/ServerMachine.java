package sampler;

import communication.actors.DefaultPushServer;
import communication.actors.Server;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;

public class ServerMachine {
    public static void main(String[] args) throws AlreadyBoundException, RemoteException {
        Server server = DefaultPushServer.createDefaultPushServer(101);
        System.err.println("Ready");

    }
}
