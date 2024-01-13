package org.question1;
import org.slf4j.LoggerFactory;

public class MessageReceiver implements Runnable {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(MessageReceiver.class);
    private MessageQueue msg;
    private int totalMessages;

    public MessageReceiver(MessageQueue msg, int totalMessages) {
        this.msg = msg;
        this.totalMessages = totalMessages;
    }

    public void run() {
        int i = 0;
        while (i < totalMessages) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                logger.error("Error occurred while waiting");
            }
            msg.getMsg();
            i++;
        }
    }
}
