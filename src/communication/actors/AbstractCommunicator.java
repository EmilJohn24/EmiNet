package communication.actors;

import communication.message.Message;

import java.rmi.RemoteException;
import java.util.AbstractSet;
import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

class AbstractCommunicator implements Communicator{
    private final SubmissionPublisher<Message> publisher; //MARKED: for transfer to hypothetical skeleton class

    AbstractCommunicator(){
        this.publisher = createPublisher();
    }

    private static MessagePublisher createPublisher(){
        return new MessagePublisher();
    }

    //AbstractCommunicator automatically blocks any attempts to speaking/communicating with the class itself. This
    //prevents it from the client/server from sending things to themselves and helps avoid the semantic mistakes made by the author
    //when using speak()
    @Override
    public final void speak(Message msg, Communicator sender) throws RemoteException, Communicator.SelfSendException {
        //interpret message here
        //for now, it will print the message in the system out stream
        if (sender == this) throw new Communicator.SelfSendException("Sending to self not allowed");
        System.err.println("Received message: " + msg);
        publisher.submit(msg);
    }



    @Override
    public void subscribe(Flow.Subscriber<? super Message> subscriber) {
        publisher.subscribe(subscriber);
    }

    @Override
    public void ping() throws RemoteException, SelfSendException {
        //TODO: implement later
        this.speak(Message.PING_MESSAGE, Communicator.ANONYMOUS);
    }


}
