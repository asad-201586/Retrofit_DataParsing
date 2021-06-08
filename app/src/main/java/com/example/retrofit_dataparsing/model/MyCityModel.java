package com.example.retrofit_dataparsing.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class MyCityModel implements Serializable
{
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("data")
    @Expose
    private ArrayList<CityModel> cityList = null;
    private final static long serialVersionUID = 7049509761681442790L;

    public MyCityModel() {
    }

    /**
     *
     * @param msg
     * @param cityList
     * @param status
     */
    public MyCityModel(String msg, Boolean status, ArrayList<CityModel> cityList) {
        super();
        this.msg = msg;
        this.status = status;
        this.cityList = cityList;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public ArrayList<CityModel> getCityList() {
        return cityList;
    }

    public void setCityList(ArrayList<CityModel> cityList) {
        this.cityList = cityList;
    }

}
