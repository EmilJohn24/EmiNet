package communication.actors;

import communication.message.Message;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

public class DefaultPushServer extends AbstractCommunicator implements Server{
    private Collection<Client> clients;
    private final Registry registry;


    public static Server createDefaultPushServer(int port) throws RemoteException, AlreadyBoundException {
        Registry registry = LocateRegistry.createRegistry(port);
        Server preservedStub = (Server) UnicastRemoteObject.exportObject(new DefaultPushServer(registry), 0);
        registry.bind(Server.DEFAULT_SERVICE_NAME, preservedStub);
        return preservedStub;
    }


    private DefaultPushServer(Registry registry){
        this.clients = new ArrayList<>();
        this.registry = registry;

    }

    @Override
    public void register(Client client) throws RemoteException{
        clients.add(client);
    }

    @Override
    public void unregister(Client client) throws RemoteException{
        clients.remove(client);
    }

    @Override
    public void pushMessage(Message msg) throws RemoteException, SelfSendException {
        for (Client client : clients){
            client.speak(msg, this);
        }
    }




    @Override
    public void ping() throws RemoteException, SelfSendException {
        this.speak(Message.PING_MESSAGE, Communicator.ANONYMOUS);
    }
}
