public class ChatUser {
    private String name;
    private boolean isAdmin;
    
    public ChatUser(String name, boolean isAdmin) {
        this.name = name;
        this.isAdmin = isAdmin;
    }
    
    public String getName() {
        return name;
    }
    
    public boolean isAdmin() {
        return isAdmin;
    }
    
    public void sendMessage(ChatGroup chatGroup, String message) {
        chatGroup.broadcastMessage(this, message);
    }
    
    public void receiveMessage(String senderName, String message) {
        System.out.println(name + " received a message from " + senderName + ": " + message);
    }
}
