package communication.actors;

import communication.message.Message;

import java.rmi.RemoteException;

public interface Server extends Communicator {
    String DEFAULT_SERVICE_NAME = "EMI_NET";

    void register(Client client) throws RemoteException;
    void unregister(Client client) throws RemoteException;
    void pushMessage(Message msg) throws RemoteException, SelfSendException;

}
