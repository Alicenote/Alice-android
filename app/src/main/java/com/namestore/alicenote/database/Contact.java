package com.namestore.alicenote.database;

/**
 * Created by Nhocnhinho on 5/26/2015.
 */


public class Contact {
    private int id;
    private String name;

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private String birthday;
    private String phoneNumber;

    private String email;
    private String address;
    private String comment;
    // private String notification;
   /* public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

*/

    /*public String getNowTime() {
        return nowTime;
    }

    public void setNowTime(String nowTime) {
        this.nowTime = nowTime;
    }*/

    //  private String nowTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Contact() {
    }

    public Contact(String name, String phoneNumber, String birthday, String email, /*String nowTime,*/ String address, String comment/*, String notification*/) {
        super();
        this.name = name;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;

        this.email = email;
        this.address = address;
        this.comment = this.comment;
        // this.nowTime =nowTime;
        //  this.notification= notification;
    }


    public Contact(int id, String name, String birthday, String phoneNumber, String email, /*String nowTime,*/ String address, String comment/*, String notification*/) {
        super();
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;

        this.email = email;
        this.address = address;
        this.comment = comment;
        //   this.nowTime =nowTime;
        //    this.notification=notification;
    }


}


