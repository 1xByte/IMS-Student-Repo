package com.student.model;

import lombok.*;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
public class Student {

    private String studentId;

    private String studentName;

    private String studentAddress;

    @Transient
    private List<Course> courses= new ArrayList<>();


}
