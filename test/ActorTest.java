import communication.actors.Client;
import communication.actors.DefaultPushClient;
import communication.actors.DefaultPushServer;
import communication.actors.Server;
import communication.message.Message;
import communication.message.TestMessage;
import org.testng.annotations.Test;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.concurrent.Flow;

//TODO: Write better tests
@Test
public class ActorTest implements Flow.Subscriber<Message> {


//    public void testServer() throws AlreadyBoundException, RemoteException, NotBoundException {
//        Server newServer = DefaultPushServer.createDefaultPushServer(420);
//        Client clientOne = DefaultPushClient.createClientTo("localhost", 420);
//        newServer.subscribe(this);
//        clientOne.speak((new TestMessage.MessageBuilder()).timeNow().description("Hello").title("Hello").build(), );
//    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        subscription.request(10);
    }

    @Override
    public void onNext(Message item) {
        System.out.println(item);
    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onComplete() {

    }
}
