package com.webingate.paysmartbusinessapp.repository.media;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.webingate.paysmartbusinessapp.object.MediaMp3Obj;

import java.util.List;

/**
 * Created by Panacea-Soft on 7/19/18.
 * Contact Email : teamps.is.cool@gmail.com
 */


public class MediaMp3Repository {
    public static List<MediaMp3Obj> getCategoryList() {
        return new Gson().fromJson(json, new TypeToken<List<MediaMp3Obj>>() {}.getType());
    }

    private static String json = "[\n" +
            "  {\n" +
            "    \"title\":\"Sweet Child O'Mine\",\n" +
            "    \"image\":\"media_img_1\",\n" +
            "    \"singer\":\"Guns N'Roses\",\n" +
            "    \"song\":\"bensound_happyrock\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\":\"Back in Black\",\n" +
            "    \"image\":\"media_img_2\",\n" +
            "    \"singer\":\"AC/DC\",\n" +
            "    \"song\":\"bensound_highoctane\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\":\"November Rain\",\n" +
            "    \"image\":\"media_img_3\",\n" +
            "    \"singer\":\"Guns N'Roses\",\n" +
            "    \"song\":\"bensound_rumble\"\n" +
            "  }\n" +
            "]";
}
