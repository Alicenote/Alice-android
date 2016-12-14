package com.namestore.alicenote.network.reponse;

import android.provider.ContactsContract;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.namestore.alicenote.network.BaseResponse;

/**
 * Created by nhocnhinho on 12/12/2016.
 */

public class VenueViewResponse extends BaseResponse {

    @SerializedName("data")
    @Expose

    Data data;
    public Data getData() {
        return data;
    }

    public class Data {


        @SerializedName("location")
        @Expose
        Locations locations;
        public Locations getLocations() {
            return locations;
        }

        public class Locations {

            @SerializedName("name")
            @Expose
            String nameSalon;

            @SerializedName("image")
            @Expose
            String imageCover;


            @SerializedName("image_avatar")
            @Expose
            String imageAvatar;

            @SerializedName("description")
            @Expose
            String description;

            @SerializedName("telephone")
            @Expose
            String telephone;

            @SerializedName("latitude")
            @Expose
            String latitude;

            @SerializedName("longitude")
            @Expose
            String longitude;

            @SerializedName("postcode")
            @Expose
            String postcode;

            @SerializedName("address")
            @Expose
            String address;

            @SerializedName("email")
            @Expose
            String email;

            @SerializedName("website")
            @Expose
            String website;

            @SerializedName("point")
            @Expose
            int point;

            @SerializedName("no_review")
            @Expose
            int no_review;

            @SerializedName("client")
            @Expose
            Client client;

            public String getImageAvatar() {
                return imageAvatar;
            }

            public String getDescription() {
                return description;
            }

            public String getTelephone() {
                return telephone;
            }

            public String getLatitude() {
                return latitude;
            }

            public String getLongitude() {
                return longitude;
            }

            public String getPostcode() {
                return postcode;
            }

            public String getAddress() {
                return address;
            }

            public String getEmail() {
                return email;
            }

            public String getWebsite() {
                return website;
            }

            public int getPoint() {
                return point;
            }

            public int getNo_review() {
                return no_review;
            }

            public Client getClient() {
                return client;
            }

            public String getNameSalon() {
                return nameSalon;
            }

            public String getImageCover() {
                return imageCover;
            }

            public class Client {

                @SerializedName("comment")
                @Expose
                String comment;

                @SerializedName("updated")
                @Expose
                String updated;

                @SerializedName("id")
                @Expose
                int id;

                @SerializedName("name")
                @Expose
                String name;

                @SerializedName("avatar")
                @Expose
                String avatar;

                public String getComment() {
                    return comment;
                }

                public String getUpdated() {
                    return updated;
                }

                public int getId() {
                    return id;
                }

                public String getName() {
                    return name;
                }

                public String getAvatar() {
                    return avatar;
                }
            }


        }

    }


}
