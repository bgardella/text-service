package org.gardella.underarmour.textservice.models;


import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue
    private int id;

    @OneToOne(optional = true)
    @NotFound(action = NotFoundAction.IGNORE)
    private User user;

    private String text;
    private Date expirationDate;


    public ExpiredMessage expire() {
        ExpiredMessage msg = new ExpiredMessage();
        msg.setId(id);
        msg.setUser(user);
        msg.setText(text);
        msg.setExpirationDate(expirationDate);
        return msg;
    }

    //////////////////////////////////
    //////////////////////////////////
    //////////////////////////////////


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

}
