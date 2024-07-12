package org.question2;
import org.slf4j.LoggerFactory;

public class MessageSender implements Runnable {
    public static final org.slf4j.Logger logger = LoggerFactory.getLogger(MessageSender.class);
    private MessageQueue msg;
    private int totalMessages;

    public MessageSender(MessageQueue msg, int totalMessages) {
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
            i++;
            msg.setMsg("Hello " + i);
        }
    }
}
