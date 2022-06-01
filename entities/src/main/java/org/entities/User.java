package org.entities;

import javax.persistence.*;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public class User extends Guest implements Serializable {

    @Column(name = " Identification")
    private String userID;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "pass_word")
    private String password;
    @Column(name="usermail")
    private String email;
    private String phoneNum;
    private Boolean frozen=false;
    private Boolean connected;
    @ManyToOne
    private Store store = null;

    public User(String userID, String name, String userName, String password, String email, String phoneNum ,Store store) {
        super(name);
        this.userID=userID;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.phoneNum=phoneNum;
        this.connected=false;
        this.store=store;
    }

    public User() {
        super();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getConnected() {
        return connected;
    }

    public void setConnected(Boolean connected) {
        this.connected = connected;
    }

    public String getID() {
        return userID;
    }

    public void setID(String ID) {
        this.userID = userID;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public void setFrozen(Boolean frozen) {
        this.frozen = frozen;
    }

    public Boolean getFrozen() {
        return frozen;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}
