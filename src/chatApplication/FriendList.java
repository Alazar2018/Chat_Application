package chatApplication;

import java.io.*;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import exception.SystemExceptionHandler;

public class FriendList {
    private String filePath;
    private List<String> friends;

    public FriendList(String filePath) {
        this.filePath = filePath;
        this.friends = new ArrayList<>();
        readFriendList();
    }

    public void readFriendList() {
        try (BufferedReader friendListReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = friendListReader.readLine()) != null) {
                friends.add(line);
            }
        } catch (FileNotFoundException e) {
            SystemExceptionHandler.hadleException(e);
            System.out.println("Friend list file not found: " + e.getMessage());
        } catch (IOException e) {
            SystemExceptionHandler.hadleException(e);
            e.printStackTrace();
        }
    }

    public void addFriend(String fullName) {
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            String ipAddress = localHost.getHostAddress();
            String friendEntry = "[Full name]: "+fullName + "/n" +"[IP Address]: " + ipAddress + "/n [Image]: null";
            friends.add(friendEntry);

            try (BufferedWriter friendListWriter = new BufferedWriter(new FileWriter(filePath, true))) {
                friendListWriter.write(friendEntry);
                friendListWriter.newLine();
            } catch (IOException e) {
                SystemExceptionHandler.hadleException(e);
                e.printStackTrace();
            }
        } catch (Exception e) {
            SystemExceptionHandler.hadleException(e);
            e.printStackTrace();
        }
    }

    public List<String> getFriends() {
        return friends;
    }

    public void printFriends() {
        for (String friend : friends) {
            System.out.println(friend);
        }
    }
}
