import javax.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;


class User {
    private String name;
    private boolean isAdmin;
    private Session session;
    private Topic chatTopic;
    private MessageProducer producer;
    private MessageConsumer consumer;
    
    public User(String name, boolean isAdmin, Session session, Topic chatTopic) throws JMSException {
        this.name = name;
        this.isAdmin = isAdmin;
        this.session = session;
        this.chatTopic = chatTopic;
        
        // Create the message producer
        producer = session.createProducer(chatTopic);
        
        // Create the message consumer
        consumer = session.createConsumer(chatTopic);
        consumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                try {
                    if (message instanceof TextMessage) {
                        TextMessage textMessage = (TextMessage) message;
                        System.out.println(name + " received a message: " + textMessage.getText());
                    }
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public void joinGroup() throws JMSException {
        System.out.println(name + " joined the chat group.");
    }
    
    public void leaveGroup() throws JMSException {
        System.out.println(name + " left the chat group.");
    }
    
    public void sendMessage(String message) throws JMSException {
        TextMessage textMessage = session.createTextMessage();
        textMessage.setText(name + " sent a message: " + message);
        producer.send(textMessage);
    }
}
