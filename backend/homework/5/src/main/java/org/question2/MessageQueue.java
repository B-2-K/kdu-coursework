package org.question2;
import org.slf4j.LoggerFactory;
public class MessageQueue {
    public static final org.slf4j.Logger logger = LoggerFactory.getLogger(MessageQueue.class);
    private String msg;
    private boolean flag = false;
    private int totalMessages;
    private int receivedMessages = 0;

    public MessageQueue(int totalMessages) {
        this.totalMessages = totalMessages;
    }

    public synchronized void setMsg(String msg) {
        while (flag) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                logger.error("Error occurred while waiting");
            }
        }

        this.msg = msg;
        logger.info("Sent : {}", msg);
        flag = true;
        notifyAll();
    }

    public synchronized void getMsg() {
        while (!flag || receivedMessages >= totalMessages) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                logger.error("Error occurred while waiting");
            }
        }

        logger.info("Received : {}", msg);
        receivedMessages++;

        if (receivedMessages == totalMessages) {
            notifyAll();
        } else {
            flag = false;
        }

    }
}
