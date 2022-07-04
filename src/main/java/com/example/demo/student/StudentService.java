package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private static StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository =  studentRepository;
    }

    public static void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());

        if(studentOptional.isPresent()){
            throw new IllegalStateException("email token");
        }
        studentRepository.save(student);
        System.out.println(student);
    }

    public static void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if(!exists){
            throw new IllegalStateException("student with id" + studentId + "does not exist");
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public static void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException(
                        "Student with " + studentId + " Does not exist"
                ));
        if(name != null && name.length() > 0){
            student.setName(name);
        }

        if(email !=null && email.length() > 0){
             Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
             if(studentOptional.isPresent()){
                 throw new IllegalStateException("email taken");
             }
            student.setEmail(email);
        }
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

}
