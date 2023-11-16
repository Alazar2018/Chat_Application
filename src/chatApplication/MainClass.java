/** *
 * Added exceptions and Added A system.log to log exception details, including the timestamp, exception type, message, and stack trace
 * 
 * Author: Rediet Teklay
*/


package chatApplication;

import java.util.Scanner;

import exception.CustomExceptionHandler;

public class MainClass {
    public static void main(String[] args) throws CustomExceptionHandler {
        Scanner scanner = new Scanner(System.in);

        String publicFilePath = "C:\\Users\\hp\\Documents\\gebaya\\spring\\chatApplication\\files\\Eurakarte.log";
        String privateFilePath = "C:\\Users\\hp\\Documents\\gebaya\\spring\\chatApplication\\files\\PrivateMessages.log";
        String friendListFilePath = "C:\\Users\\hp\\Documents\\gebaya\\spring\\chatApplication\\files\\friends.list";

        PublicChat pubChat = new PublicChat(publicFilePath);
        PrivateMessage privateChat = new PrivateMessage(privateFilePath);
        FriendList friendList = new FriendList(friendListFilePath);

        boolean continueChatting = true;

        while (continueChatting) {
            System.out.println("Choose an action: ");
            System.out.println("(1) Manage Friends");
            System.out.println("(2) Chat in Public");
            System.out.println("(3) Chat in Private");
            System.out.println("Enter 1, 2, or 3: ");

            int actionChoice = scanner.nextInt();
            
            switch (actionChoice) {
                case 1:
                    manageFriends(friendList, scanner);
                    break;
                case 2:
                    chatInPublic(pubChat, scanner);
                    break;
                case 3:
                    chatInPrivate(privateChat, scanner);
                    break;
                default:
                    System.out.println("Invalid action choice. Please enter 1, 2, or 3.");
            }

            System.out.println("Do you want to continue? (1) Yes or (2) No: ");
            int continueChoice = scanner.nextInt();
            continueChatting = (continueChoice == 1);
        }

        scanner.close();
    }

    private static void manageFriends(FriendList friendList, Scanner scanner) throws CustomExceptionHandler {
        System.out.println("Do you want to (1) View Friends or (2) Add a Friend? Enter 1 or 2: ");
        int choice = scanner.nextInt();

        if (choice == 1) {
            friendList.printFriends();
        } else if (choice == 2) {
            scanner.nextLine();
            System.out.println("Enter the full name of the friend you want to add:");
            String fullName = scanner.nextLine();
            friendList.addFriend(fullName);
            System.out.println("Friend added to the list.");
        } else {
            throw new CustomExceptionHandler("Invalid choice. Please enter 1 to View Friends or 2 to Add a Friend.");
        }
    }

    private static void chatInPublic(PublicChat pubChat, Scanner scanner) throws CustomExceptionHandler {
        System.out.println("Choose an action: ");
        System.out.println("(1) Read in Public Chat");
        System.out.println("(2) Write in Public Chat");
        System.out.println("Enter 1 or 2: ");

        int choice = scanner.nextInt();

        if (choice == 1) {
            pubChat.printMessages();
        } else if (choice == 2) {
            scanner.nextLine(); 
            System.out.println("Enter the public message you want to write:");
            String message = scanner.nextLine();
            pubChat.writePublicChat("New public message: " + message);
        } else {
            throw new CustomExceptionHandler("Invalid choice. Please enter 1 to Read or 2 to Write.");
        }
    }

    private static void chatInPrivate(PrivateMessage privateChat, Scanner scanner) throws CustomExceptionHandler {
        System.out.println("Choose an action: ");
        System.out.println("(1) Read in Private Chat");
        System.out.println("(2) Write in Private Chat");
        System.out.println("Enter 1 or 2: ");

        int choice = scanner.nextInt();

        if (choice == 1) {
            privateChat.printMessages();
        } else if (choice == 2) {
            scanner.nextLine(); // Consume the newline character
            System.out.println("Enter the private message you want to write:");
            String message = scanner.nextLine();
            privateChat.writePrivateMessage("New private message: " + message);
        } else {
            throw new CustomExceptionHandler("Invalid choice. Please enter 1 to Read or 2 to Write.");
        }
    }
}
