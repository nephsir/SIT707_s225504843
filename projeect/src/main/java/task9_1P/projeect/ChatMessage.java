package task9_1P.projeect;

import java.time.LocalDateTime;

public class ChatMessage {
	
	private final String senderId;
    private final String content;
    private final LocalDateTime sentAt;

    public ChatMessage(String senderId, String content, LocalDateTime sentAt) {
    	if (senderId == null || senderId.isBlank())
    		throw new IllegalArgumentException("senderId must not be null or blank");
        if (content == null || content.isBlank())
            throw new IllegalArgumentException("content must not be null or blank");
        if (sentAt == null)
            throw new IllegalArgumentException("sentAt must not be null");
        this.senderId = senderId;
        this.content  = content;
        this.sentAt   = sentAt;
    }

    public String getSenderId() { 
    	return senderId; 
    }
    public String getContent() { 
    	return content;  
    }
    public LocalDateTime getSentAt() { 
    	return sentAt;   
    }

    @Override
    public String toString() {
        return "[" + sentAt + "] " + senderId + ": " + content;
    }

}
