public class Main {
    public static void main(String[] args) {
        ChatGroup chatGroup = new ChatGroup();
        
        // Create admin
        ChatUser admin = new ChatUser("Chairman", true);
        chatGroup.addChatUser(admin);
        
        // Create users
        ChatUser user1 = new ChatUser("John Doe", false);
        ChatUser user2 = new ChatUser("Jane Smith", false);
        chatGroup.addChatUser(user1);
        chatGroup.addChatUser(user2);
        
        // Send messages
        admin.sendMessage(chatGroup, "Welcome to the community chat group!");
        user1.sendMessage(chatGroup, "Hi everyone, I'm new here.");
        user2.sendMessage(chatGroup, "Nice to meet you, John!");
        
        // Remove a user from the group
        chatGroup.removeChatUser(user1);
        
        // Send a message after user1 has left
        user2.sendMessage(chatGroup, "Too bad John left. We'll miss him!");
    }
}
