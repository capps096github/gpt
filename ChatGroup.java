import java.util.ArrayList;
import java.util.List;

public class ChatGroup {
    private List<ChatUser> users;
    
    public ChatGroup() {
        this.users = new ArrayList<>();
    }
    
    public void addChatUser(ChatUser user) {
        users.add(user);
        System.out.println(user.getName() + " joined the chat group.");
    }
    
    public void removeChatUser(ChatUser user) {
        users.remove(user);
        System.out.println(user.getName() + " left the chat group.");
    }
    
    public void broadcastMessage(ChatUser sender, String message) {
        System.out.println(sender.getName() + " sent a message: " + message);
        
        for (ChatUser user : users) {
            if (!user.equals(sender)) {
                user.receiveMessage(sender.getName(), message);
            }
        }
    }
}
