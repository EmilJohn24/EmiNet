package communication.actors;

import communication.message.Message;

import java.io.Serializable;
import java.util.concurrent.SubmissionPublisher;

class MessagePublisher extends SubmissionPublisher<Message> implements Serializable {

}
