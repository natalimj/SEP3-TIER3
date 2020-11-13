package sep3.classes.Model;

import java.time.LocalDateTime;

public class Message {

    private int messageId;
    private int senderId;
    private int receiverId;
    private LocalDateTime timestamp;
    private String messageType;
    private String text;

    public Message(int senderId, int receiverId, LocalDateTime timestamp, String messageType, String text) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.timestamp = timestamp;
        this.messageType = messageType;
        this.text = text;
    }

    public Message(int messageId, int senderId, int receiverId, LocalDateTime timestamp, String messageType, String text) {
        this.messageId = messageId;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.timestamp = timestamp;
        this.messageType = messageType;
        this.text = text;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}


