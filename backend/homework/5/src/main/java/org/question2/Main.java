package org.question2;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        int totalMessages = 3;
        MessageQueue msg = new MessageQueue(totalMessages);

        // Create thread pools for sender and receiver
        ExecutorService senderThreadPool = Executors.newFixedThreadPool(1);
        ExecutorService receiverThreadPool = Executors.newFixedThreadPool(1);

        // Start sender and receiver threads using thread pools
        senderThreadPool.execute(new MessageSender(msg, totalMessages));
        receiverThreadPool.execute(new MessageReceiver(msg, totalMessages));

        // Shutdown thread pools when done
        senderThreadPool.shutdown();
        receiverThreadPool.shutdown();

    }
}
