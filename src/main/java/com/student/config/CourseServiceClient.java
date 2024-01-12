package com.student.config;

import com.student.model.Course;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "COURSE-SERVICE")
public interface CourseServiceClient {

    @GetMapping(value = "/get-course-by-student-id/{studentId}")
    List<Course> getCourses(@PathVariable("studentId") String studentId);

    @GetMapping(value = "/test")
    String getTest();

    @GetMapping(value ="/get-course-by-student-name/{studentName}" )
    List<Course> getByStudentName(@PathVariable("studentName") String studentName);

}
