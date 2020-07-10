package cn.mh.springboottest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.mh.springboottest.entity.Student;
import cn.mh.springboottest.service.IStudentService;

@RestController
public class StudentController {
	@Autowired
	private IStudentService studentService;

	@GetMapping("/list")
	public Object list() {
		List<Student> list = studentService.listAll();
		if (list == null)
			return "{\"msg\":\"无数据\"}";
		return studentService.listAll();
	}

	@RequestMapping("/insert")
	public String insert() {
		
		for (int i = 0; i < 10; i++) {
			Student student = new Student();
			student.setStuAge(i);
			student.setStuName("test" + i);
			student.setStuNumber("100" + i);
			if (i % 2 == 0) {
				student.setStuSex("男");
			}
			else {
				student.setStuSex("女");
			}
			student.setStuId(i);
			studentService.save(student);
		}
		return "{\"msg\":\"新增成功\"}";
	}

	@PostMapping("/update")
	public String update(Student student) {
		int save = studentService.save(student);
		if (save > 0)
			return "{\"msg\":\"更新成功\"}";
		return "{\"msg\":\"更新失败\"}";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam int stuId) {
		int delete = studentService.delete(stuId);
		if (delete > 0)
			return "{\"msg\":\"删除成功\"}";
		return "{\"msg\":\"删除失败\"}";
	}
	
	@RequestMapping("/find/{id}")
	public Student findById(@PathVariable("id")  int id) {
		return studentService.findById(id);
	}
	
	@RequestMapping("/range/{lessId}/{maxId}")
	public List<Student> findByRange(@PathVariable int lessId, @PathVariable int maxId) {
		return studentService.getStudentbyRange(lessId, maxId);
	}
}
