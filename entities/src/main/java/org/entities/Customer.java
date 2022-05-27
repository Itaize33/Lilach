package org.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "customers")
public class Customer extends User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public enum AccountType{STORE, CHAIN, MEMBERSHIP}
    private AccountType accountType;
    private Date memberShipExpire;
    private int balance=0;
    private String creditCard;

    @OneToMany(targetEntity = Complaint.class, mappedBy = "customer")
    @Column(name = "order")
    protected List<Order> orders = new LinkedList<Order>();

    @OneToMany(targetEntity = Complaint.class, mappedBy = "customer")
    @Column(name = "complaint")
    private List<Complaint> complaints = new LinkedList<Complaint>();

    public Customer(String userID, String name, String userName, String password, String email, String phone, String creditCard, AccountType accountType,Store store) {
        super(userID, name, userName, password, email, phone, store);
        this.creditCard = creditCard;
        this.accountType = accountType;
        if(accountType == AccountType.MEMBERSHIP){
            memberShipExpire = new Date();
            memberShipExpire.setYear(memberShipExpire.getYear()+1);
        }
        //TODO add hashing to password if have time.
    }

/*    public Customer(String userID, String name, String userName, String password, String email, String phone, String creditCard, AccountType accountType, Store store) {
        super(userID, name, userName, password, email, phone);
        this.creditCard = creditCard;
        this.accountType = accountType;
        this.store = store;
        //TODO add hashing to password if have time.
    }*/

    public Customer() {
        super();
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public Date getMemberShipExpire() {
        return memberShipExpire;
    }

    //TODO delete this method!!
    public void setMemberShipExpireTODELETE(Date date){
        this.memberShipExpire = date;
    }

    public void setMemberShipExpire() {
        Date memberShipExpire = new Date();
        memberShipExpire.setYear(memberShipExpire.getYear() + 1);
        this.memberShipExpire = memberShipExpire;
    }

    public String getTypeString() {
        switch(this.accountType) {
            case STORE:
                return "Store Customer";

            case CHAIN:
                return "Chain Customer";

            default:
                return "Membership Customer";
        }
    }

    static public String[] getAllTypes(){
        String[] types = new String[3];
        types[0] = "Store Customer";
        types[1] = "Chain Customer";
        types[2] = "Membership Customer";
        return types;

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;

    }
}
