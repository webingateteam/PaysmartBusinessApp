package com.webingate.paysmartbusinessapp.object;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Panacea-Soft on 8/25/18.
 * Contact Email : teamps.is.cool@gmail.com
 */


public class EducationCategoryListWithCourse {

    @SerializedName("category_name")
    public String category_name;

    @SerializedName("course_list")
    public List<Course> courseList;

    public class Course {
        @SerializedName("course_name")
        public String courseName;
    }

}
