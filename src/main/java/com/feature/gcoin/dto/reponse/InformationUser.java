package com.feature.gcoin.dto.reponse;

import java.math.BigInteger;

public class InformationUser {
    String userName; //ten dang nhap
    String email;//mail
    String name; //name
    String address; //name
    BigInteger numberCoin; // address
    BigInteger priceCoin; // address

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigInteger getNumberCoin() {
        return numberCoin;
    }

    public void setNumberCoin(BigInteger numberCoin) {
        this.numberCoin = numberCoin;
    }

    public BigInteger getPriceCoin() {
        return priceCoin;
    }

    public void setPriceCoin(BigInteger priceCoin) {
        this.priceCoin = priceCoin;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
