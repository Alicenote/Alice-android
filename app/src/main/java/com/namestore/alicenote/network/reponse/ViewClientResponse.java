package com.namestore.alicenote.network.reponse;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.namestore.alicenote.network.BaseResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nhocnhinho on 15/11/2016.
 */

public class ViewClientResponse extends BaseResponse {

    @SerializedName("data")
    @Expose
    public Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data {

        public Client getClient() {
            return client;
        }

        public void setClient(Client client) {
            this.client = client;
        }

        @SerializedName("client")
        @Expose
        public Client client;


        public class Client {
            @SerializedName("gender")
            @Expose
            public int gender;

            @SerializedName("description")
            @Expose
            public String description;

            @SerializedName("first_name")
            @Expose
            public String firstName;

            @SerializedName("last_name")
            @Expose
            public String lastName;

            @SerializedName("email")
            @Expose
            public String email;

            @SerializedName("birthday")
            @Expose
            public String birthday;

            @SerializedName("phone")
            @Expose
            public String phone;

            @SerializedName("address")
            @Expose
            public String address;

            @SerializedName("image")
            @Expose
            public String image;

            public String getFirstName() {
                return firstName;
            }

            public void setFirstName(String firstName) {
                this.firstName = firstName;
            }

            public String getLastName() {
                return lastName;
            }

            public void setLastName(String lastName) {
                this.lastName = lastName;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getBirthday() {
                return birthday;
            }

            public void setBirthday(String birthday) {
                this.birthday = birthday;
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

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public int getGender() {
                return gender;
            }

            public void setGender(int gender) {
                this.gender = gender;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }
        }

        @SerializedName("appointments")
        @Expose
        public Appointments appointments;

        public Appointments getAppointments() {
            return appointments;
        }

        public void setAppointments(Appointments appointments) {
            this.appointments = appointments;
        }

        public class Appointments {

            public ArrayList<Appointment> getAppointment() {
                return appointment;
            }

            public void setAppointment(ArrayList<Appointment> appointment) {
                this.appointment = appointment;
            }

            @SerializedName("appointments")
            @Expose
            public ArrayList<Appointment> appointment;

            public class Appointment {

                @SerializedName("id")
                @Expose
                public int id;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }


                public String getTotalPrice() {
                    return totalPrice;
                }

                public void setTotalPrice(String totalPrice) {
                    this.totalPrice = totalPrice;
                }

                public String getStaff() {
                    return staff;
                }

                public void setStaff(String staff) {
                    this.staff = staff;
                }

                public String getService() {
                    return service;
                }

                public void setService(String service) {
                    this.service = service;
                }

                public String getStartTime() {
                    return startTime;
                }

                public void setStartTime(String startTime) {
                    this.startTime = startTime;
                }


                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }


                @SerializedName("total_price")
                @Expose
                public String totalPrice;

                @SerializedName("staff")
                @Expose
                public String staff;

                @SerializedName("service")
                @Expose
                public String service;

                @SerializedName("start_time")
                @Expose
                public String startTime;


                @SerializedName("date")
                @Expose
                public String date;


            }


        }
    }
}