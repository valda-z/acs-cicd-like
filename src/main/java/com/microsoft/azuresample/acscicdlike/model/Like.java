package com.microsoft.azuresample.acscicdlike.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by vazvadsk on 2016-12-02.
 */
public class Like {
    private String id;
    private int count;
    private Date updated;
    
    public Like(){

    }

    public Like(String id, int count, Date updated){
        this.setId(id);
        this.setCount(count);
        this.setUpdated(updated);
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("count")
    public int getCount() {
        return count;
    }

    @JsonProperty("count")
    public void setCount(int count) {
        this.count = count;
    }

    @JsonProperty("updated")
    public Date getUpdated() {
        return updated;
    }

    @JsonProperty("updated")
    public void setUpdated(Date updated) {
        this.updated = updated;
    }

}
