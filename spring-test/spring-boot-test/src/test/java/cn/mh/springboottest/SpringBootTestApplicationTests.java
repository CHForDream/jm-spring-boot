package cn.mh.springboottest;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cn.mh.springboottest.dao.StudentDao;
import cn.mh.springboottest.entity.Student;

@SpringBootTest
class SpringBootTestApplicationTests {


    @Autowired
    private StudentDao studentDao;

    // 测试 查询 student 表中所有的数据
    @Test
    public void getList(){
        List<Student> list = (List<Student>) studentDao.findAll();
        System.out.println(list);
    }
}
