package sampler;

import communication.actors.Client;
import communication.actors.Communicator;
import communication.actors.DefaultPushClient;
import communication.message.TestMessage;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ClientMachine {
    public static void main(String[] args) throws RemoteException, NotBoundException, Communicator.SelfSendException {
        Client client = DefaultPushClient.createClientTo("localhost", 101);
        client.sendToServer((new TestMessage.MessageBuilder()).title("Hello").timeNow().build());
        client.subscribe(new ClientPrinter());
    }
}
