package com.student.cont;

import com.student.config.CourseServiceClient;
import com.student.model.Course;
import com.student.model.Student;
import com.student.service.studentService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
@Slf4j
public class studentCont {

    @Autowired
    private studentService service;

    @Autowired
    private CourseServiceClient courseServiceClient;

    @PostMapping(value = "/add-student")
    public Student addStudent(@RequestBody Student student){
        return service.addStudent(student);
    }

    @GetMapping (value = "/get-all-students")
    @ResponseStatus(HttpStatus.FOUND)
    public List<Student> getAllStudent(){
        return service.getAllStudents();
    }

    @GetMapping (value = "/get-student/{id}")
   // @CircuitBreaker(name = "coursebreaker",fallbackMethod = "coursefallback")
   // @Retry(name = "courseretry",fallbackMethod = "coursefallback")
   // @RateLimiter(name = "courselimiter",fallbackMethod = "coursefallback")
    public Student getStudentById(@PathVariable String id) throws Exception {
        Student student = service.getStudentById(id);
        log.info("in student cont{}",student.getCourses() );
        return student;
    }

//    public Student coursefallback(String id,Exception e) throws Exception {
//        Student student = Student.builder().studentId("10998").studentAddress("DOWN").studentName("FAllback").build();
//        return student;
//    }

@GetMapping(value = "/get-by-studentName/{studentName}")
    public Student getByName(@PathVariable("studentName") String studentName){
    log.info("student cont....");
        return service.getStudentsByName(studentName);
    }


    @GetMapping(value = "/go")
    public  String goTest(){
        return courseServiceClient.getTest();
    }

}
