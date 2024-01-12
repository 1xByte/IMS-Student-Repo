package com.student.service;

import com.student.config.CourseServiceClient;
import com.student.model.Course;
import com.student.model.Student;
import com.student.repo.studentRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
public class studentService {

    @Autowired
    private studentRepo repo;

//    @Autowired
//    private RestTemplate template;

    @Autowired
    private CourseServiceClient courseServiceClient;

    public Student addStudent(Student student){

        String s = UUID.randomUUID().toString();
        student.setStudentId(s);

        Student student1 = repo.save(student);
        return student1;
    }

    public List<Student> getAllStudents(){
        List<Student> all = repo.findAll();

        for (Student s:all
             ) {
            s.setCourses(courseServiceClient.getCourses(s.getStudentId()));
        log.info("inside for each", s);
        }

        return all;
    }

    public  Student getStudentById(String id) throws Exception {

        Student student = repo.findByStudentId(id);
        log.info("getting student of id {}",student.getStudentId() );
      //  List<Course> courses = courseService.getCourses(student.getStudentId());
       // ArrayList list = template.getForObject("http://COURSE-SERVICE/course/get-course-by-student-id/" + student.getStudentId(), ArrayList.class);

        List<Course> courses = courseServiceClient.getCourses(student.getStudentId());
        student.setCourses(courses);

        log.info("getting COURSE of id {}",student.getStudentId());
        return student;

    }

    public Student getStudentsByName(String studentName) {
        Student student = repo.findByStudentName(studentName);
        log.info("fetching from DB{}",student);
        student.setCourses(courseServiceClient.getByStudentName(studentName));
        log.info("fetching after course DB{}",student);

        return  student;
    }
}
