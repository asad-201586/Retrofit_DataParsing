package com.example.retrofit_dataparsing.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CityModel {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("division_id")
    @Expose
    private Integer divisionId;
    private final static long serialVersionUID = -5948416769903162124L;

    public CityModel() {
    }

    /**
     *
     * @param name
     * @param id
     * @param divisionId
     */
    public CityModel(Integer id, String name, Integer divisionId) {
        super();
        this.id = id;
        this.name = name;
        this.divisionId = divisionId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDivisionId() {
        return divisionId;
    }

    public void setDivisionId(Integer divisionId) {
        this.divisionId = divisionId;
    }

}
