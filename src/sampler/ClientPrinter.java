package sampler;

import communication.message.Message;

import java.util.concurrent.Flow;

public class ClientPrinter implements Flow.Subscriber<Message> {
    @Override
    public void onSubscribe(Flow.Subscription subscription) {

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
