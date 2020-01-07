package communication.message;

import communication.actors.Server;

import java.io.Serializable;
import java.time.LocalDateTime;

//PENDING: messages should probably be immutable
//should probably have a static builder or something of the sort
public abstract class Message implements Serializable {
    private final LocalDateTime timestamp;
    private final String title;
    private final String description;

    public static final Message PING_MESSAGE = (new TestMessage.MessageBuilder()).timeNow().title("Ping").build();

    Message(MessageBuilder<?> builder){
        this.title = builder.title;
        this.description = builder.description;
        this.timestamp = builder.timestamp;
    }

    @Override
    public String toString() {
        return "Message{" +
                "timestamp=" + timestamp +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public abstract static class MessageBuilder<T extends MessageBuilder<T>>{
        private LocalDateTime timestamp;
        private String title;
        private String description;

        public T title(String title){
            this.title = title;
            return self();
        }

        public T description(String description){
            this.description = description;
            return self();
        }

        public T time(LocalDateTime timestamp){
            this.timestamp = timestamp;
            return self();
        }

        public T timeNow(){
            this.timestamp = LocalDateTime.now();
            return self();
        }

        //must return a built version based on what is returned by the private constructor
        abstract Message build();
        //must return this at every level
        protected abstract T self();
    }


}
