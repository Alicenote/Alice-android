package com.namestore.alicenote.network.reponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by kienht on 11/21/16.
 */

public class FillFirstSetupResponse {

    @SerializedName("fullname")
    @Expose
    private String ownerName;

    @SerializedName("states")
    @Expose
    private List<State> states;

    @SerializedName("businesstypes")
    @Expose
    private List<Businesstype> businesstypes;

    @SerializedName("default_services")
    @Expose
    private List<DefaultService> defaultServices;

    public class State {
        @SerializedName("id")
        @Expose
        private int stateId;

        @SerializedName("name")
        @Expose
        private  String stateName;

        public int getStateId() {
            return stateId;
        }

        public void setStateId(int stateId) {
            this.stateId = stateId;
        }

        public String getStateName() {
            return stateName;
        }

        public void setStateName(String stateName) {
            this.stateName = stateName;
        }
    }

    public class Businesstype {
        @SerializedName("id")
        @Expose
        private int bsnTypeId;

        @SerializedName("name")
        @Expose
        private String bsnTypeName;

        public int getBsnTypeId() {
            return bsnTypeId;
        }

        public void setBsnTypeId(int bsnTypeId) {
            this.bsnTypeId = bsnTypeId;
        }

        public String getBsnTypeName() {
            return bsnTypeName;
        }

        public void setBsnTypeName(String bsnTypeName) {
            this.bsnTypeName = bsnTypeName;
        }
    }

    public class DefaultService {

        @SerializedName("group")
        @Expose
        Group group;

        @SerializedName("services")
        @Expose
        List<Service> services;

        public class Group {
            @SerializedName("id")
            @Expose
            private int groupId;

            @SerializedName("name")
            @Expose
            private String groupName;

            public int getGroupId() {
                return groupId;
            }

            public void setGroupId(int groupId) {
                this.groupId = groupId;
            }

            public String getGroupName() {
                return groupName;
            }

            public void setGroupName(String groupName) {
                this.groupName = groupName;
            }
        }


        public class Service {
            @SerializedName("name")
            @Expose
            private String nameService;

            public String getNameService() {
                return nameService;
            }

            public void setNameService(String nameService) {
                this.nameService = nameService;
            }
        }


        public Group getGroup() {
            return group;
        }

        public void setGroup(Group group) {
            this.group = group;
        }

        public List<Service> getServices() {
            return services;
        }

        public void setServices(List<Service> services) {
            this.services = services;
        }

    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public List<State> getStates() {
        return states;
    }

    public void setStates(List<State> states) {
        this.states = states;
    }

    public List<Businesstype> getBusinesstypes() {
        return businesstypes;
    }

    public void setBusinesstypes(List<Businesstype> businesstypes) {
        this.businesstypes = businesstypes;
    }

    public List<DefaultService> getDefaultServices() {
        return defaultServices;
    }

    public void setDefaultServices(List<DefaultService> defaultServices) {
        this.defaultServices = defaultServices;
    }

}
