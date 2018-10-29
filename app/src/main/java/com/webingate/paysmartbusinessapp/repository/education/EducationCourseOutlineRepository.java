package com.webingate.paysmartbusinessapp.repository.education;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.webingate.paysmartbusinessapp.object.EducationCourseOutline;

import java.util.List;

/**
 * Created by Panacea-Soft on 8/24/18.
 * Contact Email : teamps.is.cool@gmail.com
 */


public class EducationCourseOutlineRepository {

    public static List<EducationCourseOutline> getEducationCourseOutlineList() {

        return new Gson().fromJson(videoJson, new TypeToken<List<EducationCourseOutline>>() {
        }.getType());
    }

    static String videoJson = "[\n" +
            "  {\n" +
            "    \"category_name\": \"Basic trigonometric ratios\",\n" +
            "    \"course_list\": [\n" +
            "      {\n" +
            "        \"video_name\":\"The basic of trigonometry: an introduction to triangles.\",\n" +
            "        \"status\":\"done\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"video_name\":\"Example: Using SOH CAH TOA\",\n" +
            "        \"status\":\"done\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"video_name\":\"Basic trigonometry II\",\n" +
            "        \"status\":\"progress\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"video_name\":\"Example: Trig to solve the sides and angles of a right triangle.\",\n" +
            "        \"status\":\"not_yet\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"video_name\":\"Trigonometry 2\",\n" +
            "        \"status\":\" not_yet\"\n" +
            "      }\n" +
            "    ]\n" +
            "  },\n" +
            "  {\n" +
            "    \"category_name\": \"Trigonometric ratios and similarity\",\n" +
            "    \"course_list\": [\n" +
            "      {\n" +
            "        \"video_name\":\"Similarity to define sine, cosine, and tangent.\",\n" +
            "        \"status\":\"not_yet\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"video_name\":\"Sine and cosine of complements example.\",\n" +
            "        \"status\":\"not_yet\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"video_name\":\"Showing relationship between cosine and sine of complements.\",\n" +
            "        \"status\":\"not_yet\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"video_name\":\"Example with trig functions and ratios.\",\n" +
            "        \"status\":\"not_yet\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"video_name\":\"Trigonometric functions.\",\n" +
            "        \"status\":\"not_yet\"\n" +
            "      }\n" +
            "    ]\n" +
            "  }\n" +
            "]";

}