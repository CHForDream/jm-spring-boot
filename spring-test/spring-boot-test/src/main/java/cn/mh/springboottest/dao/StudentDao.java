package cn.mh.springboottest.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import cn.mh.springboottest.entity.Student;


public interface StudentDao extends CrudRepository<Student,Integer> {
	public Student findById(int id);
	
	public Student findByStuAge(int stuAge);
	
	@Query(value = "select * from student where stu_age > :lessAge and stu_age < :maxAge", nativeQuery = true)
	public List<Student> findStudentInAge(@Param("lessAge") int lessAge, @Param("maxAge") int maxAge);
}
