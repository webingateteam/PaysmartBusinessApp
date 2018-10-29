package com.webingate.paysmartbusinessapp.repository.userprofile;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.webingate.paysmartbusinessapp.object.UserProfile;

import java.util.ArrayList;

/**
 * Created by Panacea-Soft on 7/4/18.
 * Contact Email : teamps.is.cool@gmail.com
 */


public class UserProfileRepository {
    public static ArrayList<UserProfile> getProfileList() {
        return new Gson().fromJson(json, new TypeToken<ArrayList<UserProfile>>() {}.getType());
    }

    static String json = "[\n" +
            "  {\n" +
            "    \"image\":\"profile1\",\n" +
            "    \"name\":\"Oliver\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"image\":\"profile2\",\n" +
            "    \"name\":\"George\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"image\":\"man_profile\",\n" +
            "    \"name\":\"Harry\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"image\":\"woman_profile\",\n" +
            "    \"name\":\"Jack\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"image\":\"profile1\",\n" +
            "    \"name\":\"Jacob\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"image\":\"profile2\",\n" +
            "    \"name\":\"Charlie\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"image\":\"man_profile\",\n" +
            "    \"name\":\"Noah\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"image\":\"woman_profile\",\n" +
            "    \"name\":\"Joshua\"\n" +
            "  }\n" +
            "]";
}
