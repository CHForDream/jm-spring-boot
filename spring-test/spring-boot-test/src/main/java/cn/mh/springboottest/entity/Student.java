package cn.mh.springboottest.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//实体类的标志
@Entity
//对应的数据库表名
@Table(name = "student")
public class Student {

	@Id
//	@GeneratedValue(strategy = GenerationType.TABLE) // 自增长主键
	private int stuId;

	// 数据库字段名 不区分大小写
	// 数据库名列为stu_number Hibernate 会按照驼峰命名规范帮我们转换成 stuNumber
	// @Column(name = "stu_number")
	private String stuNumber;

	private String stuName;
	private int stuAge;
	private String stuSex;

	public int getStuId() {
		return stuId;
	}

	public void setStuId(int stuId) {
		this.stuId = stuId;
	}

	public String getStuNumber() {
		return stuNumber;
	}

	public void setStuNumber(String stuNumber) {
		this.stuNumber = stuNumber;
	}

	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	public int getStuAge() {
		return stuAge;
	}

	public void setStuAge(int stuAge) {
		this.stuAge = stuAge;
	}

	public String getStuSex() {
		return stuSex;
	}

	public void setStuSex(String stuSex) {
		this.stuSex = stuSex;
	}

	@Override
	public String toString() {
		return "Student{" + "stuId=" + stuId + ", stuNumber='" + stuNumber + '\'' + ", stuName='" + stuName + '\''
				+ ", stuAge=" + stuAge + ", stuSex='" + stuSex + '\'' + '}';
	}
}
