package com.namestore.alicenote.models;

import java.util.ArrayList;

/**
 * Created by kienht on 12/6/16.
 */

public class ReportObj {

    public static final int TOP_SERVICES = 0;
    public static final int TOP_EMPLOYEE = 1;
    public static final int REPORT_VIEW = 2;

    public ReportObj() {
    }

    public ReportObj(int TAG, String title, ArrayList<Data> datas) {
        this.title = title;
        this.datas = datas;
        this.TAG = TAG;
    }

    int TAG;

    private String title;

    private ArrayList<Data> datas;

    public int getTAG() {
        return TAG;
    }

    public void setTAG(int TAG) {
        this.TAG = TAG;
    }

    public ArrayList<Data> getDatas() {
        return datas;
    }

    public void setDatas(ArrayList<Data> datas) {
        this.datas = datas;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public class Data {

        public Data(String stt, int uriImg, String name, String rank, int books) {
            this.stt = stt;
            this.uriImg = uriImg;
            this.name = name;
            this.rank = rank;
            this.books = books;
        }

        private String stt;
        private int uriImg;
        private String name;
        private String rank;
        private int books;

        public String getStt() {
            return stt;
        }

        public void setStt(String stt) {
            this.stt = stt;
        }

        public int getUriImg() {
            return uriImg;
        }

        public void setUriImg(int uriImg) {
            this.uriImg = uriImg;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRank() {
            return rank;
        }

        public void setRank(String rank) {
            this.rank = rank;
        }

        public int getBooks() {
            return books;
        }

        public void setBooks(int books) {
            this.books = books;
        }
    }

}
