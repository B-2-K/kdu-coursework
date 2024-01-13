package org.question1;

public class Main {
    public static void main(String[] args) {
        int totalMessages = 3;

        // Creating objects of MessageQueue, MessageSender, MessageReceiver
        MessageQueue msg = new MessageQueue(totalMessages);
        MessageSender sender = new MessageSender(msg, totalMessages);
        MessageReceiver receiver = new MessageReceiver(msg, totalMessages);

        // Start sender and receiver threads
        new Thread(sender, "MessageSender").start();
        new Thread(receiver, "MessageReceiver").start();
    }
}
