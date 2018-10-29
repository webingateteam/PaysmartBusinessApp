package com.webingate.paysmartbusinessapp.repository.education;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.webingate.paysmartbusinessapp.object.EducationCourseCollection;

import java.util.List;

/**
 * Created by Panacea-Soft on 8/25/18.
 * Contact Email : teamps.is.cool@gmail.com
 */


public class EducationCourseCollectionRepository {
    public static List<EducationCourseCollection> getCourseCollection() {
        List<EducationCourseCollection> educationCourseCollectionList = new Gson().fromJson(json, new TypeToken<List<EducationCourseCollection>>() {}.getType());

        return educationCourseCollectionList;
    }

    static String json = "[\n" +
            "  {\n" +
            "    \"name\":\"Linear Regression\",\n" +
            "    \"status\":\"In-Progress\",\n" +
            "    \"image\":\"education_course_1_img\",\n" +
            "    \"progress\":\"20\",\n" +
            "    \"type\":\"NANO-DEGREE PROGRAM\",\n" +
            "    \"color\":\"green\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\":\"Logistic Regression\",\n" +
            "    \"status\":\"Not Started\",\n" +
            "    \"image\":\"education_course_2_img\",\n" +
            "    \"progress\":\"0\",\n" +
            "    \"type\":\"NANO-DEGREE PROGRAM\",\n" +
            "    \"color\":\"yellow\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\":\"Decision Trees\",\n" +
            "    \"status\":\"Not Started\",\n" +
            "    \"image\":\"education_course_3_img\",\n" +
            "    \"progress\":\"0\",\n" +
            "    \"type\":\"NANO-DEGREE PROGRAM\",\n" +
            "    \"color\":\"blue\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\":\"Naive Bayes\",\n" +
            "    \"status\":\"Not Started\",\n" +
            "    \"image\":\"education_course_4_img\",\n" +
            "    \"progress\":\"0\",\n" +
            "    \"type\":\"NANO-DEGREE PROGRAM\",\n" +
            "    \"color\":\"red\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\":\"Support Vector Machines\",\n" +
            "    \"status\":\"Not Started\",\n" +
            "    \"image\":\"education_course_5_img\",\n" +
            "    \"progress\":\"0\",\n" +
            "    \"type\":\"NANO-DEGREE PROGRAM\",\n" +
            "    \"color\":\"orange\"\n" +
            "  }\n" +
            "]";
}
