package com.student.repo;

import com.student.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface studentRepo extends MongoRepository<Student,String> {

    Student findByStudentId(String id);

    Student findByStudentName(String studentName);
}
