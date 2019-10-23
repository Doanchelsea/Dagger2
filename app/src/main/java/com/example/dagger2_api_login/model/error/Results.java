
package com.example.dagger2_api_login.model.error;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Results  {

    @SerializedName("error")
    @Expose
    private Error_ error;

    public Error_ getError() {
        return error;
    }

    public void setError(Error_ error) {
        this.error = error;
    }

    @SerializedName("message")
    @Expose
    private String messages;
    

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }
}
