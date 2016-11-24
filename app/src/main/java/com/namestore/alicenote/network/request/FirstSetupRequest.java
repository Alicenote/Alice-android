package com.namestore.alicenote.network.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by kienht on 11/9/16.
 */

public class FirstSetupRequest {

    @SerializedName("infor")
    @Expose
    Infor infor;

    @SerializedName("schedule")
    @Expose
    ArrayList<Schedule> schedules;

    @SerializedName("service")
    @Expose
    ArrayList<Service> services;

    public Infor getInfor() {
        return infor;
    }

    public void setInfor(Infor infor) {
        this.infor = infor;
    }

    public ArrayList<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(ArrayList<Schedule> schedules) {
        this.schedules = schedules;
    }

    public ArrayList<Service> getServices() {
        return services;
    }

    public void setServices(ArrayList<Service> services) {
        this.services = services;
    }

    public class Infor {

        public Infor(String businessName, int businessType, int stateId, String city, String postcode, String address) {
            this.businessName = businessName;
            this.businessType = businessType;
            this.stateId = stateId;
            this.city = city;
            this.postcode = postcode;
            this.address = address;
        }

        @SerializedName("business_name")
        @Expose
        String businessName;

        @SerializedName("business_type")
        @Expose
        int businessType;

        @SerializedName("state_id")
        @Expose
        int stateId;

        @SerializedName("city")
        @Expose
        String city;

        @SerializedName("postcode")
        @Expose
        String postcode;

        @SerializedName("address")
        @Expose
        String address;


        public String getBusinessName() {
            return businessName;
        }

        public void setBusinessName(String businessName) {
            this.businessName = businessName;
        }

        public int getBusinessType() {
            return businessType;
        }

        public void setBusinessType(int businessType) {
            this.businessType = businessType;
        }

        public int getStateId() {
            return stateId;
        }

        public void setStateId(int stateId) {
            this.stateId = stateId;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getPostcode() {
            return postcode;
        }

        public void setPostcode(String postcode) {
            this.postcode = postcode;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }

    public class Schedule {

        public Schedule(int day, String nameday, String openhour, String closehour, String status) {
            this.day = day;
            this.nameday = nameday;
            this.openhour = openhour;
            this.closehour = closehour;
            this.status = status;
        }

        @SerializedName("day")
        @Expose
        int day;

        @SerializedName("nameday")
        @Expose
        String nameday;

        @SerializedName("openhour")
        @Expose
        String openhour;

        @SerializedName("closehour")
        @Expose
        String closehour;

        @SerializedName("status")
        @Expose
        String status;

        public int getDay() {
            return day;
        }

        public void setDay(int day) {
            this.day = day;
        }

        public String getNameday() {
            return nameday;
        }

        public void setNameday(String nameday) {
            this.nameday = nameday;
        }

        public String getOpenhour() {
            return openhour;
        }

        public void setOpenhour(String openhour) {
            this.openhour = openhour;
        }

        public String getClosehour() {
            return closehour;
        }

        public void setClosehour(String closehour) {
            this.closehour = closehour;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }

    public class Service {

        public Service() {

        }

        public Service(Group group, ArrayList<SubServices> subServices) {
            this.group = group;
            this.subServices = subServices;
        }

        @SerializedName("group")
        @Expose
        Group group;

        @SerializedName("services")
        @Expose
        ArrayList<SubServices> subServices;

        public Group getGroup() {
            return group;
        }

        public void setGroup(Group group) {
            this.group = group;
        }

        public ArrayList<SubServices> getSubServices() {
            return subServices;
        }

        public void setSubServices(ArrayList<SubServices> subServices) {
            this.subServices = subServices;
        }

        public class Group {

            public Group(int id, String nameGroup) {
                this.id = id;
                this.nameGroup = nameGroup;
            }

            @SerializedName("id")
            @Expose
            int id;

            @SerializedName("name")
            @Expose
            String nameGroup;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getNameGroup() {
                return nameGroup;
            }

            public void setNameGroup(String name) {
                this.nameGroup = name;
            }
        }

        public class SubServices {

            public SubServices() {
            }

            public SubServices(String nameService, boolean check, int price, int duration, boolean work) {
                this.nameService = nameService;
                this.check = check;
                this.price = price;
                this.duration = duration;
                this.work = work;
            }

            @SerializedName("name")
            @Expose
            String nameService;

            @SerializedName("check")
            @Expose
            boolean check;

            @SerializedName("price")
            @Expose
            float price;

            @SerializedName("duration")
            @Expose
            int duration;

            @SerializedName("work")
            @Expose
            boolean work;

            public String getNameService() {
                return nameService;
            }

            public void setNameService(String name) {
                this.nameService = name;
            }

            public boolean isCheck() {
                return check;
            }

            public void setCheck(boolean check) {
                this.check = check;
            }

            public float getPrice() {
                return price;
            }

            public void setPrice(float price) {
                this.price = price;
            }

            public float getDuration() {
                return duration;
            }

            public void setDuration(int duration) {
                this.duration = duration;
            }

            public boolean isWork() {
                return work;
            }

            public void setWork(boolean work) {
                this.work = work;
            }
        }

    }

}
