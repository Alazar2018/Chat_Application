package chatApplication;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import exception.SystemExceptionHandler;

public class PrivateMessage {
    private String filePath;
    private List<String> messages;

    public PrivateMessage(String filePath) {
        this.filePath = filePath;
        this.messages = new ArrayList<>();
        readPrivateMessages();
    }

    public void readPrivateMessages() {
        try (BufferedReader privateChat = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = privateChat.readLine()) != null) {
                messages.add(line);
            }
        } catch (FileNotFoundException e) {
            SystemExceptionHandler.hadleException(e);
            System.out.println("Private Chat file not found: " + e.getMessage());
        } catch (IOException e) {
            SystemExceptionHandler.hadleException(e);
            e.printStackTrace();
        }
    }

    public void writePrivateMessage(String message) {
        try (BufferedWriter privateChat = new BufferedWriter(new FileWriter(filePath, true))) {
            privateChat.write(message);
            privateChat.newLine();
            messages.add(message);
        } catch (IOException e) {
            SystemExceptionHandler.hadleException(e);
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

