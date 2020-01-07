package communication.actors;


import communication.message.Message;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.Flow;

public interface Client extends Communicator {
    class AlreadyConnectedException extends Throwable {
        AlreadyConnectedException(String message){
            super(message);
        }
    }

    class ClientConnector {
        private ClientConnector() { }

        static Server connectToServerService(String host, int port, String serviceName) throws RemoteException, NotBoundException {
//            UnicastRemoteObject.exportObject(this, 0);
            Registry registry = LocateRegistry.getRegistry(host, port);
            return (Server) registry.lookup(serviceName);

        }
    }
    void sendToServer(Message msg) throws RemoteException, SelfSendException;


}
