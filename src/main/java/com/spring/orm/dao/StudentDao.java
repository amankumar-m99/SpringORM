package com.spring.orm.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.orm.hibernate5.HibernateTemplate;

import com.spring.orm.entities.Student;

public class StudentDao {

	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	// create/insert student
	@Transactional() // to enable write operation on database
	public int insert(Student student) {
		Integer i = (Integer) hibernateTemplate.save(student);
		return i;
	}

	// get single student
	public Student getStudent(int id) {
		Student student = this.hibernateTemplate.get(Student.class, id);
		return student;
	}

	// get All students
	public List<Student> getAllStudents(){
		List<Student> students = this.hibernateTemplate.loadAll(Student.class);
		return students;
	}

	// update student
	@Transactional
	public void updateStudent(Student student) {
		this.hibernateTemplate.update(student);
	}

	// delete students
	@Transactional
	public void deleteStudent(int id) {
		Student student = getStudent(id);
		this.hibernateTemplate.delete(student);
	}

}
