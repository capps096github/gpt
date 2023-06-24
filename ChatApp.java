// import javax.jms.*;
// import org.apache.activemq.ActiveMQConnectionFactory;


// public class ChatApp {
//     public static void main(String[] args) {
//         try {
//             // Create a connection factory
//             ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
            
//             // Create a connection
//             Connection connection = connectionFactory.createConnection();
//             connection.start();
            
//             // Create a session
//             Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            
//             // Create the chat group topic
//             Topic chatTopic = session.createTopic("CommunityChatGroup");
            
//             // Create the chairman as the admin
//             User admin = new User("Chairman", true, session, chatTopic);
//             admin.joinGroup();
            
//             // Create other users
//             User user1 = new User("John Doe", false, session, chatTopic);
//             User user2 = new User("Jane Smith", false, session, chatTopic);
//             user1.joinGroup();
//             user2.joinGroup();
            
//             // Send messages
//             admin.sendMessage("Welcome to the community chat group!");
//             user1.sendMessage("Hi everyone, I'm new here.");
//             user2.sendMessage("Nice to meet you, John!");
            
//             // Remove a user from the group
//             user1.leaveGroup();
            
//             // Send a message after user1 has left
//             user2.sendMessage("Too bad John left. We'll miss him!");
            
//             // Close the connections
//             session.close();
//             connection.close();
//         } catch (JMSException e) {
//             e.printStackTrace();
//         }
//     }
// }



import javax.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;
import java.util.Scanner;

public class ChatApp {
    public static void main(String[] args) throws ClassNotFoundException{
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

            // Prompt for the admin's name
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter admin's name: ");
            String adminName = scanner.nextLine();

            // Create the chairman as the admin
            User admin = new User(adminName, true, session, chatTopic);
            admin.joinGroup();

            // Prompt for other users' names
            System.out.print("Enter user1's name: ");
            String user1Name = scanner.nextLine();
            System.out.print("Enter user2's name: ");
            String user2Name = scanner.nextLine();

            // Create other users
            User user1 = new User(user1Name, false, session, chatTopic);
            User user2 = new User(user2Name, false, session, chatTopic);
            user1.joinGroup();
            user2.joinGroup();

            // Prompt for and send messages
            System.out.print("Enter welcome message: ");
            String welcomeMessage = scanner.nextLine();
            admin.sendMessage(welcomeMessage);

            System.out.print("Enter message from user1: ");
            String user1Message = scanner.nextLine();
            user1.sendMessage(user1Message);

            System.out.print("Enter message from user2: ");
            String user2Message = scanner.nextLine();
            user2.sendMessage(user2Message);

            // Remove a user from the group
            user1.leaveGroup();

            // Prompt for and send a message after user1 has left
            System.out.print("Enter message from user2 after user1 has left: ");
            String user2MessageAfterLeave = scanner.nextLine();
            user2.sendMessage(user2MessageAfterLeave);

            // Close the connections
            session.close();
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
