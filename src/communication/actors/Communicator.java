package communication.actors;

import communication.message.Message;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.concurrent.Flow;

//TODO: consider creating skeleton classes for Client and Server
//https://docs.oracle.com/javase/9/docs/api/java/util/concurrent/SubmissionPublisher.html
//
/*
* dictates the actions that can be done by the clients to the server
 */
public interface Communicator extends Remote, Serializable {
    //callable by all actors if they need a publisher to feed the messages to (when using the messages in various parts of the app)

    Communicator ANONYMOUS = new AnonymousCommunicator();

    class AnonymousCommunicator implements Communicator {
        //does nothing but serves as an identifier for classes that need to self-sendToServer
        private AnonymousCommunicator(){}
        @Override
        public void speak(Message msg, Communicator sender) throws RemoteException { }

        @Override
        public void subscribe(Flow.Subscriber<? super Message> subscriber) throws RemoteException { }

        @Override
        public void ping() throws RemoteException { }
    }

    class SelfSendException extends Exception {
        SelfSendException(String msg){
            super(msg);
        }
    }


    void speak(Message msg, Communicator sender) throws RemoteException, SelfSendException;
    void subscribe(Flow.Subscriber<? super Message> subscriber) throws RemoteException;
    void ping() throws RemoteException, SelfSendException;

}
