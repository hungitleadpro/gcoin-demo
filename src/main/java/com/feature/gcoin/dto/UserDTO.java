package com.feature.gcoin.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.feature.gcoin.model.Authority;
import com.feature.gcoin.model.BaseEntity;
import org.joda.time.DateTime;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class UserDTO extends BaseDTO {

    private Long id; //id 1

    private String username; //ten dang nhap 2

    private String email; //email 3

    private String name; //name 4

    private String type; //loai 7

    private Date lastPasswordResetDate;

    private String phone;

    private String address;

    private int numberVote;

    private BigInteger numberCoin; // address

    private BigInteger priceCoin; // address

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

    public UserDTO() {
    }

    private List<Authority> authorities;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    public void setLastPasswordResetDate(Date lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getNumberVote() {
        return numberVote;
    }

    public void setNumberVote(int numberVote) {
        this.numberVote = numberVote;
    }
}
