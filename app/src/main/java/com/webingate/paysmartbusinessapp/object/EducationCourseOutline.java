package com.webingate.paysmartbusinessapp.object;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Panacea-Soft on 8/24/18.
 * Contact Email : teamps.is.cool@gmail.com
 */


public class EducationCourseOutline {

    @SerializedName("category_name")
    public String courseName;

    @SerializedName("course_list")
    public List<EducationCourseOutline.Course> courseList;

    public EducationCourseOutline(String courseName, List<Course> courseList) {
        this.courseName = courseName;
        this.courseList = courseList;
    }

    public class Course {
        @SerializedName("video_name")
        public String videoName;

        @SerializedName("status")
        public String status;
    }

}
