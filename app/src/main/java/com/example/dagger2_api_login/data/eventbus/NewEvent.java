package com.example.dagger2_api_login.data.eventbus;

public class NewEvent {
    private String tripPackgeId;

    public NewEvent(String tripPackgeId) {
        this.tripPackgeId = tripPackgeId;
    }

    public String getTripPackgeId() {
        return tripPackgeId;
    }
}
