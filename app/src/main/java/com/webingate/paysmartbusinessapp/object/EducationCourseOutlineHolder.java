package com.webingate.paysmartbusinessapp.object;

/**
 * Created by Panacea-Soft on 8/24/18.
 * Contact Email : teamps.is.cool@gmail.com
 */


public class EducationCourseOutlineHolder {

    public String courseName;
    public EducationCourseOutline.Course course;
    public boolean isHeader;
    public boolean isTop;
    public boolean isBottom;

    public EducationCourseOutlineHolder(String courseName, EducationCourseOutline.Course course, boolean isHeader, boolean isTop, boolean isBottom) {
        this.courseName = courseName;
        this.course = course;
        this.isHeader = isHeader;
        this.isTop = isTop;
        this.isBottom = isBottom;
    }

    public EducationCourseOutlineHolder(String courseName, boolean isHeader) {
        this.courseName = courseName;
        this.isHeader = isHeader;
    }
}
