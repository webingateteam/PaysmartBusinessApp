package com.webingate.paysmartbusinessapp.repository.social;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.webingate.paysmartbusinessapp.object.TimelineSocial;

import java.util.List;

/**
 * Created by Panacea-Soft on 7/21/18.
 * Contact Email : teamps.is.cool@gmail.com
 */


public class TimelineSocialRepository {
    public static List<TimelineSocial> getTimelineSocialList() {
        return new Gson().fromJson(json, new TypeToken<List<TimelineSocial>>() {}.getType());
    }

    private static String json = "[\n" +
            "  {\n" +
            "    \"date\":\"21:14\",\n" +
            "    \"name\":\"Adam Applessed\",\n" +
            "    \"user_image\":\"profile2\",\n" +
            "    \"detail\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. \",\n" +
            "    \"icon\":\"baseline_mail_24\",\n" +
            "    \"image\":\"\",\n" +
            "    \"type\":\"post\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"date\":\"20:20\",\n" +
            "    \"name\":\"Adam Applessed\",\n" +
            "    \"user_image\":\"profile2\",\n" +
            "    \"detail\":\"Coffee Time\",\n" +
            "    \"icon\":\"baseline_insert_photo_black_24\",\n" +
            "    \"image\":\"cafe1\",\n" +
            "    \"type\":\"image\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"date\":\"20:10\",\n" +
            "    \"name\":\"Marcus\",\n" +
            "    \"user_image\":\"profile1\",\n" +
            "    \"detail\":\"just liked your picture\",\n" +
            "    \"icon\":\"baseline_heart_white\",\n" +
            "    \"image\":\"\",\n" +
            "    \"type\":\"like_status\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"date\":\"19:10\",\n" +
            "    \"name\":\"Adam Applessed\",\n" +
            "    \"user_image\":\"profile2\",\n" +
            "    \"detail\":\"Lorem\",\n" +
            "    \"icon\":\"baseline_insert_photo_black_24\",\n" +
            "    \"image\":\"city_japan\",\n" +
            "    \"type\":\"image\"\n" +
            "  }\n" +
            "\n" +
            "]";
}
