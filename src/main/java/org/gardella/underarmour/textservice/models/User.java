package org.gardella.underarmour.textservice.models;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue
    private int id;

    private String username;

    @OneToMany(mappedBy="user")
    @NotFound(action = NotFoundAction.IGNORE)
    //@OrderBy("createdAt DESC")
    private List<Message> messageList;


    //////////////////////////////////
    //////////////////////////////////
    //////////////////////////////////

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }
}
