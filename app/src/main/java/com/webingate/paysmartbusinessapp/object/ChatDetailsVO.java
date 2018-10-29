package com.webingate.paysmartbusinessapp.object;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Panacea-Soft on 9/7/18.
 * Contact Email : teamps.is.cool@gmail.com
 */


public class ChatDetailsVO {

    @SerializedName("id")
    private String id;

    @SerializedName("by_user")
    private String user;

    @SerializedName("profile_image")
    private String profileImage;

    @SerializedName("msg")
    private String message;

    @SerializedName("timestamp")
    private String timestamp;

    @SerializedName("state")
    private String state;

    @SerializedName("type")
    private String type;

    public ChatDetailsVO(String id, String user, String profileImage, String message, String timestamp, String state, String type) {
        this.id = id;
        this.user = user;
        this.profileImage = profileImage;
        this.message = message;
        this.timestamp = timestamp;
        this.state = state;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getUser() {
        return user;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public String getMessage() {
        return message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getState() {
        return state;
    }

    public String getType() {
        return type;
    }
}
