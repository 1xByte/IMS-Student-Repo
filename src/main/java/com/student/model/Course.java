package com.student.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    private  String courseId;
    private String courseName;
    private  String courseFee;
    private String studentId;
    private String studentName;
}
