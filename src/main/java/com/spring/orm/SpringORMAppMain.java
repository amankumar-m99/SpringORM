package com.spring.orm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.orm.dao.StudentDao;
import com.spring.orm.entities.Student;

public class SpringORMAppMain {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
		StudentDao studentDao = context.getBean("studentDao", StudentDao.class);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean exit = false;
		while (!exit) {
			showMenu();
			try {
				int menu = Integer.parseInt(br.readLine());
				switch (menu) {
				case 1:
					insertStudent(br, studentDao);
					break;
				case 2:
					getAllStudents(br, studentDao);
					break;
				case 3:
					getStudent(br, studentDao);
					break;
				case 4:
					updateStudent(br, studentDao);
					break;
				case 5:
					deleteStudent(br, studentDao);
					break;
				case 6:
					exit();
					exit = true;
					break;
				default:
				}
			} catch (Exception e) {
				System.out.println("Invalid input.");
				System.out.println(e.getMessage());
			}
		}
		((ClassPathXmlApplicationContext) context).close();
	}

	private static void showMenu() {
		int menuId = 1;
		System.out.println("Menu ---------------");
		System.out.println(menuId++ + " : Add new student");
		System.out.println(menuId++ + " : Display all students");
		System.out.println(menuId++ + " : Get details of single student");
		System.out.println(menuId++ + " : Update a student");
		System.out.println(menuId++ + " : Delete a student");
		System.out.println(menuId++ + " : Exit");
		System.out.println("-----------------------------------");
		System.out.print("Input: ");
	}

	private static void insertStudent(BufferedReader br, StudentDao studentDao) throws IOException {
		System.out.println("Enter student name:");
		String name = br.readLine();
		System.out.println("Enter student city:");
		String city = br.readLine();
		Student student = new Student(name, city);
		studentDao.insert(student);
		System.out.println("Student added successfully!");
	}

	private static void getAllStudents(BufferedReader br, StudentDao studentDao) {
		List<Student> students = studentDao.getAllStudents();
		for (Student student : students) {
			System.out.println(student);
		}
		System.out.println("Found " + students.size() + " student(s)");
	}

	private static void getStudent(BufferedReader br, StudentDao studentDao) throws NumberFormatException, IOException {
		System.out.println("Enter the student id:");
		int id = Integer.parseInt(br.readLine());
		Student student = studentDao.getStudent(id);
		if (student == null) {
			System.out.println("No student found with id " + id + " !!!");
		} else {
			System.out.println(student);
		}
	}

	private static void updateStudent(BufferedReader br, StudentDao studentDao)
			throws NumberFormatException, IOException {
		System.out.println("Enter the student id:");
		int id = Integer.parseInt(br.readLine());
		Student student = studentDao.getStudent(id);
		if (student == null) {
			System.out.println("No student found with id " + id + " !!!");
			return;
		}
		System.out.println(student);
		System.out.println("Enter student name:");
		String name = br.readLine();
		System.out.println("Enter student city:");
		String city = br.readLine();
		System.out.println("Update ? (Y/N):");
		String update = br.readLine();
		if (update.equalsIgnoreCase("y")) {
			student.setName(name);
			student.setCity(city);
			studentDao.updateStudent(student);
			System.out.println("Student with id " + id + " updated successfully!");
		} else {
			System.out.println("Updation aborted !!!");
		}
	}

	private static void deleteStudent(BufferedReader br, StudentDao studentDao)
			throws NumberFormatException, IOException {
		System.out.println("Enter the student id:");
		int id = Integer.parseInt(br.readLine());
		Student student = studentDao.getStudent(id);
		if (student == null) {
			System.out.println("No student found with id " + id + " !!!");
		} else {
			studentDao.deleteStudent(id);
			System.out.println("Student with id " + id + " deleted successfully!");
		}
	}

	private static void exit() {
		System.out.println("------ Thank you ----------");
	}

}
