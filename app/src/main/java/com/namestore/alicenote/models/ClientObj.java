package com.namestore.alicenote.models;

import java.io.Serializable;

/**
 * Created by kienht on 12/10/16.
 */

public class ClientObj implements Serializable {

    private String mName;
    private String mEmail;
    private String mTimeOnline;
    private String mPhone;
    private int mGender;
    private String mDob;
    private boolean mReceive;

    public ClientObj() {
    }

    public ClientObj(String mName, String mEmail, String mTimeOnline, String mPhone, int mGender, String mDob, boolean mReceive) {
        this.mName = mName;
        this.mEmail = mEmail;
        this.mTimeOnline = mTimeOnline;
        this.mPhone = mPhone;
        this.mGender = mGender;
        this.mDob = mDob;
        this.mReceive = mReceive;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getmTimeOnline() {
        return mTimeOnline;
    }

    public void setmTimeOnline(String mTimeOnline) {
        this.mTimeOnline = mTimeOnline;
    }

    public String getmPhone() {
        return mPhone;
    }

    public void setmPhone(String mPhone) {
        this.mPhone = mPhone;
    }

    public int getmGender() {
        return mGender;
    }

    public void setmGender(int mGender) {
        this.mGender = mGender;
    }

    public String getmDob() {
        return mDob;
    }

    public void setmDob(String mDob) {
        this.mDob = mDob;
    }

    public boolean ismReceive() {
        return mReceive;
    }

    public void setmReceive(boolean mReceive) {
        this.mReceive = mReceive;
    }
}
