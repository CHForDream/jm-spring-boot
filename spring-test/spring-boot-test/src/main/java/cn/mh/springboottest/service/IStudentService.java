package cn.mh.springboottest.service;

import java.util.List;

import cn.mh.springboottest.entity.Student;

public interface IStudentService {
    List<Student> listAll();

    int save(Student student);

    int delete(int stuId);

    int update(Student student);
    
    Student findById(int id);
    
    List<Student> getStudentbyRange(int lessAge, int maxAge);
}
