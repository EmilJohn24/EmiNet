package sampler;

import communication.actors.DefaultPushServer;
import communication.actors.Server;
import communication.message.Message;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.util.concurrent.Flow;

public class ServerPrinter implements Flow.Subscriber<Message> {

    @Override
    public void onSubscribe(Flow.Subscription subscription) {

    }

    @Override
    public void onNext(Message item) {
        System.err.println(item);
    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onComplete() {

    }
}
