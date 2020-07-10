package cn.mh.springboottest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.mh.springboottest.dao.StudentDao;
import cn.mh.springboottest.entity.Student;
import cn.mh.springboottest.service.IStudentService;

@Service
public class StudentServiceImpl implements IStudentService {

	@Autowired
    private StudentDao studentDao;

    @Override
    public List<Student> listAll() {
        return (List<Student>) studentDao.findAll();
    }

    @Override
    public int save(Student student) {
        return studentDao.save(student)!= null ? 1:0;
    }

    public Student findById(int id) {
    	return studentDao.findByStuAge(id);	
    }
    
    @Override
    public int delete(int stuId) {
        int i = 0;
        try {
            studentDao.deleteById(stuId);
            i = 1;
        } catch (Exception e) {
            e.printStackTrace();
            i = 0;
        }
        return i;
    }

    @Override
    public int update(Student student) {
        return studentDao.save(student) !=null ? 1:0;
    }

	@Override
	public List<Student> getStudentbyRange(int lessAge, int maxAge) {
		return studentDao.findStudentInAge(lessAge, maxAge);
	}
}
