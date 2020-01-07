package communication.actors;

import communication.message.Message;


import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

//TODO: write a wrapper class for this class

public final class DefaultPushClient extends AbstractCommunicator implements Client {
    private final Server server;

    private DefaultPushClient(String host, int port) throws RemoteException, NotBoundException {
        this.server = ClientConnector.connectToServerService(host, port, Server.DEFAULT_SERVICE_NAME); //uses default service name
        this.server.register(this);
    }

    @Override
    public void sendToServer(Message msg) throws RemoteException, SelfSendException {
        server.speak(msg, this);
    }

    //static factory for clients
    public static Client createClientTo(String host, int port) throws RemoteException, NotBoundException {
        return new DefaultPushClient(host, port);
    }






}
