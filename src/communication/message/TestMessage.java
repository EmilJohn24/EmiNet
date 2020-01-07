package communication.message;

public class TestMessage extends Message {

    private TestMessage(MessageBuilder builder){
        super(builder);
    }
    public static class MessageBuilder extends Message.MessageBuilder<MessageBuilder>{

        public MessageBuilder(){
            super();
        }

        @Override
        public Message build() {
            return new TestMessage(this);
        }

        @Override
        protected MessageBuilder self() {
            return this;
        }


    }
}
