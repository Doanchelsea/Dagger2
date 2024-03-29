
package com.example.dagger2_api_login.model.history;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class History {

    @SerializedName("code")
    @Expose
    private Long code;
    @SerializedName("count")
    @Expose
    private Long count;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("results")
    @Expose
    private ResultsHis results;

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ResultsHis getResults() {
        return results;
    }

    public void setResults(ResultsHis results) {
        this.results = results;
    }
}
