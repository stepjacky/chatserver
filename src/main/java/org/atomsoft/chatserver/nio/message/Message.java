package org.atomsoft.chatserver.nio.message;

public interface Message {
    int getMtype();
    void setMtype(int t);
    Object getBody();
    void setBody(Object body);
    
}
