package chatApplication;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PublicChat {
    private String filePath;
    private List<String> messages;

    public PublicChat(String filePath) {
        this.filePath = filePath;
        this.messages = new ArrayList<>();
        readPublicChat();
    }

    public void readPublicChat() {
    	String line;
        try (BufferedReader pubChat = new BufferedReader(new FileReader(filePath))) {
            
            while ((line = pubChat.readLine()) != null) {
                messages.add(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Public Chat file not found: " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writePublicChat(String message) {
        try (BufferedWriter chat = new BufferedWriter(new FileWriter(filePath, true))) {
            chat.write(message);
            chat.newLine();
            messages.add(message); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getMessages() {
        return messages;
    }

    public void printMessages() {
        for (String message : messages) {
            System.out.println(message);
        }
    }

    
}
