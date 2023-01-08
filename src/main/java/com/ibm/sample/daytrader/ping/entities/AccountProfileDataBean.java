package com.ibm.sample.daytrader.ping.entities;

import com.ibm.sample.daytrader.ping.utils.Log;
import com.ibm.sample.daytrader.ping.utils.TradeConfig;

public class AccountProfileDataBean implements java.io.Serializable {

    /* Accessor methods for persistent fields */

    private String userID;              /* userID */
    private String passwd;              /* password */
    private String fullName;            /* fullName */
    private String address;             /* address */
    private String email;               /* email */
    private String creditCard;          /* creditCard */
// Moved unused field into the user aggregate;
//	private AccountDataBean account;

//    @Version
//    private Integer optLock;

    public AccountProfileDataBean() {
    }

    public AccountProfileDataBean(String userID,
                                  String password,
                                  String fullName,
                                  String address,
                                  String email,
                                  String creditCard) {
        setUserID(userID);
        setPassword(password);
        setFullName(fullName);
        setAddress(address);
        setEmail(email);
        setCreditCard(creditCard);
    }

    public static AccountProfileDataBean getRandomInstance() {
        return new AccountProfileDataBean(
                TradeConfig.rndUserID(),                        // userID
                TradeConfig.rndUserID(),                        // passwd
                TradeConfig.rndFullName(),                      // fullname
                TradeConfig.rndAddress(),                       // address
                TradeConfig.rndEmail(TradeConfig.rndUserID()),  //email
                TradeConfig.rndCreditCard()                     // creditCard
        );
    }

    public String toString() {
        return "\n\tAccount Profile Data for userID:" + getUserID()
                + "\n\t\t   passwd:" + getPassword()
                + "\n\t\t   fullName:" + getFullName()
                + "\n\t\t    address:" + getAddress()
                + "\n\t\t      email:" + getEmail()
                + "\n\t\t creditCard:" + getCreditCard()
                ;
    }

    public String toHTML() {
        return "<BR>Account Profile Data for userID: <B>" + getUserID() + "</B>"
                + "<LI>   passwd:" + getPassword() + "</LI>"
                + "<LI>   fullName:" + getFullName() + "</LI>"
                + "<LI>    address:" + getAddress() + "</LI>"
                + "<LI>      email:" + getEmail() + "</LI>"
                + "<LI> creditCard:" + getCreditCard() + "</LI>"
                ;
    }

    public void print() {
        Log.log(this.toString());
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPassword() {
        return passwd;
    }

    public void setPassword(String password) {
        this.passwd = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

// Moved unused field into the user aggregate;
//    public AccountDataBean getAccount() {
//        return account;
//    }
//
//    public void setAccount(AccountDataBean account) {
//        this.account = account;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.userID != null ? this.userID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AccountProfileDataBean)) {
            return false;
        }
        AccountProfileDataBean other = (AccountProfileDataBean)object;
        if (this.userID != other.userID && (this.userID == null || !this.userID.equals(other.userID))) return false;
        return true;
    }
}

