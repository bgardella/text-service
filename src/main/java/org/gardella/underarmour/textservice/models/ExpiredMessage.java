package org.gardella.underarmour.textservice.models;


import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "expired_message")
public class ExpiredMessage {

    @Id
    private int id;

    @OneToOne(optional = true)
    @NotFound(action = NotFoundAction.IGNORE)
    private User user;

    private String text;
    private Date expirationDate;

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
