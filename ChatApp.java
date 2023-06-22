import javax.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;


public class ChatApp {
    public static void main(String[] args) {
        try {
            // Create a connection factory
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
            
            // Create a connection
            Connection connection = connectionFactory.createConnection();
            connection.start();
            
            // Create a session
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            
            // Create the chat group topic
            Topic chatTopic = session.createTopic("CommunityChatGroup");
            
            // Create the chairman as the admin
            User admin = new User("Chairman", true, session, chatTopic);
            admin.joinGroup();
            
            // Create other users
            User user1 = new User("John Doe", false, session, chatTopic);
            User user2 = new User("Jane Smith", false, session, chatTopic);
            user1.joinGroup();
            user2.joinGroup();
            
            // Send messages
            admin.sendMessage("Welcome to the community chat group!");
            user1.sendMessage("Hi everyone, I'm new here.");
            user2.sendMessage("Nice to meet you, John!");
            
            // Remove a user from the group
            user1.leaveGroup();
            
            // Send a message after user1 has left
            user2.sendMessage("Too bad John left. We'll miss him!");
            
            // Close the connections
            session.close();
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
