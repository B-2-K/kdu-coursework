package org.question1;
import org.slf4j.LoggerFactory;

public class MessageSender implements Runnable {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(Main.class);
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
                logger.error("Error occurred while waiting");
            }
            i++;
            msg.setMsg("Hello " + i);
        }
    }
}
