package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.AppDAO;
import com.luv2code.cruddemo.entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {
		return runner -> {
			//createCourseAndStudents(appDAO);
			//findCourseAndStudents(appDAO);
			//findStudentAndCourses(appDAO);
			//addMoreCoursesForStudent(appDAO);
			//deleteCourse(appDAO);
			deleteStudent(appDAO);
		};
	}

	private void deleteStudent(AppDAO appDAO) {
		int theId = 1;
		System.out.println("Deleting student id: " + theId);
		appDAO.deleteStudentByid(theId);
		System.out.println("Done!!");
	}

	private void addMoreCoursesForStudent(AppDAO appDAO) {
		int theId = 1;
		Student theStudent = appDAO.findStudentAndCoursesByStudentId(theId);

		// create new courses
		Course course1 = new Course("Rubik's Cube - How to Speed Cube");
		Course course2 = new Course("Atari 2600 - Game Development");

		// add the courses for theStudent
		theStudent.addCourse(course1);
		theStudent.addCourse(course2);

		// save the student and courses
		System.out.println("Saving student id: " + theId + " and his/her courses");
		appDAO.update(theStudent);
		System.out.println("Done!!");
	}

	private void findStudentAndCourses(AppDAO appDAO) {
		int theId = 1;
		Student theStudent = appDAO.findStudentAndCoursesByStudentId(theId);
		System.out.println("Student: " + theStudent);
		System.out.println("associated courses: " + theStudent.getCourses());
	}

	private void findCourseAndStudents(AppDAO appDAO) {
		int theId = 10;
		Course theCourse = appDAO.findCourseAndStudentsByCourseId(theId);

		System.out.println("Course: " + theCourse);
		System.out.println("associated students: " + theCourse.getStudents());
	}

	private void createCourseAndStudents(AppDAO appDAO) {
		// create the course
		Course tempCourse = new Course("Pacman - How to Score a Million Points");

		// create students
		Student student1 = new Student("John","Doe","john@luv2code.com");
		Student student2 = new Student("Mary","Public","mary@luv2code.com");

		// addstudents to the course
		tempCourse.addStudent(student1);
		tempCourse.addStudent(student2);

		// save the course and associated students
		System.out.println("Saving the course: " + tempCourse);
		System.out.println("associated students: " + tempCourse.getStudents());
		appDAO.saveCourse(tempCourse);
		System.out.println("Done!!");
	}

	private void deleteCourseAndReviews(AppDAO appDAO) {
		int theId = 10;
		System.out.println("Deleting the course id: " + theId);
		appDAO.deleteCourseById(theId);
		System.out.println("Done!!");
	}

	private void retrieveCourseAndReviews(AppDAO appDAO) {
		int theId = 10;
		Course tempCourse = appDAO.findCourseAndReviewsByCourseId(theId);
		System.out.println("The Course: " + tempCourse);
		System.out.println("Course reviews: " + tempCourse.getReviews());
		System.out.println("Done!!");
	}

	private void createCourseAndReviews(AppDAO appDAO) {
		// create a course
		Course tempCourse = new Course("Pacman - How to score 1,000,000 points");

		// add some reviews
		tempCourse.addReview(new Review("Great course. Loved it!"));
		tempCourse.addReview(new Review("Cool course. Job well done!"));
		tempCourse.addReview(new Review("What a dumb course! You are an idiot!"));

		// save the course ... and leverage the cascade all
		System.out.println("Saving the course ...");
		System.out.println(tempCourse);
		System.out.println("Reviews: " + tempCourse.getReviews());
		appDAO.saveCourse(tempCourse);
		System.out.println("Done!!");

	}

	private void deleteCourse(AppDAO appDAO) {
		int theId = 10;
		System.out.println("Deleting course id: " + theId);
		appDAO.deleteCourseById(theId);
		System.out.println("Done!!");
	}

	private void updateCourse(AppDAO appDAO) {
		int theId = 10;
		System.out.println("Finding the course ID: " + theId);

		Course theCourse = appDAO.findCourseById(theId);
		System.out.println("Changing course title...");
		theCourse.setTitle("The Beautiful Things of Life");
		System.out.println("Updating the course ID: "+theId);
		appDAO.update(theCourse);
		System.out.println("Done!!");
	}

	private void updateInstructor(AppDAO appDAO) {
		int theId = 1;
		System.out.println("Finding instructor ID: " + theId);
		Instructor tempInstructor = appDAO.findInstructorById(theId);
		System.out.println("Setting a new last name for the instructor");
		tempInstructor.setLastName("TESTER");
		System.out.println("Updating instructor...");
		appDAO.updateInstructor(tempInstructor);
		System.out.println("Done!!");
	}

	private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {
		int theId = 1;
		System.out.println("Finding instructor id: "+theId);
		Instructor tempInstructor = appDAO.findInstructorByIdJoinFetch(theId);
		System.out.println("the Instructor: " + tempInstructor);
		System.out.println("Associated courses: " + tempInstructor.getCourses());

		System.out.println("Done!!");
	}

	private void findCoursesForInstructor(AppDAO appDAO) {
		int theId = 1;
		System.out.println("Finding instructor id: " + theId);
		Instructor tempInstructor = appDAO.findInstructorById(theId);

		System.out.println("The instructor: " + tempInstructor);

		List<Course> courses = appDAO.findCoursesByInstructorId(theId);
		tempInstructor.setCourses(courses);

		System.out.println("The associated courses: " + tempInstructor.getCourses());

		System.out.println("Done!!");
	}

	private void findInstructorWithCourses(AppDAO appDAO) {
		int theId = 1;
		System.out.println("Finding instructor id: " + theId);
		Instructor tempInstructor = appDAO.findInstructorById(theId);

		System.out.println("The instructor: " + tempInstructor);
		System.out.println("The associated courses: " + tempInstructor.getCourses());

		System.out.println("Done!!");
	}

	private void createInstructorWithCourses(AppDAO appDAO) {

		Instructor tempInstructor =
				new Instructor("Susan", "Public", "susan.public@luv2code.com");

		// create the instructor detail
		InstructorDetail tempInstructorDetail =
				new InstructorDetail("http://www.youtube.com",
						"Video Games");


		// associate the objects
		tempInstructor.setInstructorDetail(tempInstructorDetail);

		// create some courses

		Course tempCourse1 = new Course("Air Guitar - The Ultimate Guide");
		Course tempCourse2 = new Course("Pinball Master Class");

		// add courses to the instructor
		tempInstructor.add(tempCourse1);
		tempInstructor.add(tempCourse2);

		// save the instructor
		// Note: This will also save the courses
		// because of CascadeType.PERSIST
		System.out.println("Saving instructor: "+tempInstructor);
		System.out.println("Instructor courses: "+tempInstructor.getCourses());
		appDAO.save(tempInstructor);

		System.out.println("Done!!");

	}

	private void deleteInstructorDetail(AppDAO appDAO) {
		int theId = 3;
		System.out.println("Deleting Instructor Detail ID: " + theId);
		appDAO.deleteInstructorDetailById(theId);
		System.out.println("Deleting done!!");
	}

	private void findInstructorDetail(AppDAO appDAO) {
		int theId = 2;

		// get the InstructorDetail object
		InstructorDetail tempInstructorDetail = appDAO.findInstructorDetailById(theId);

		// print the instructor detail
		System.out.println("Instructor details are: "+tempInstructorDetail.toString());

		// print the associated instructor
		System.out.println("The associated instructor is: "+tempInstructorDetail.getInstructor().toString());

		System.out.println("Done!");
	}

	private void deleteInstructor(AppDAO appDAO) {
		int theId = 1;
		System.out.println("Deleting the instructor with ID: "+theId);
		appDAO.deleteById(theId);
		System.out.println("Done deleting!");
	}

	private void findInstructor(AppDAO appDAO) {

		int theId = 1;
		System.out.println("Finding the instructor with the id: "+theId);

		Instructor tempInstructor = appDAO.findInstructorById(theId);

		System.out.println("tempInstructor: " + tempInstructor);
		System.out.println("The associate instructorDetail only: "+tempInstructor.getInstructorDetail());
	}


	private void createInstructor(AppDAO appDAO) {
		/*
		// create the instructor

		Instructor tempInstructor =
				new Instructor("Chad", "Darby", "darby@luv2code.com");

		// create the instructor detail
		InstructorDetail tempInstructorDetail =
				new InstructorDetail("http://www.luv2code.com/youtube",
						"Luv 2 code!!");

		*/
		// create the instructor

		Instructor tempInstructor =
				new Instructor("Madhu", "Patel", "madhu@luv2code.com");

		// create the instructor detail
		InstructorDetail tempInstructorDetail =
				new InstructorDetail("http://www.luv2code.com/youtube",
						"Guitar");


		// associate the objects
		tempInstructor.setInstructorDetail(tempInstructorDetail);

		// save the instructor
		//
		//	NOTE: this will ALSO save the details object
		// 	because of CascadeType.ALL
		//
		System.out.println("Saving instructor: "+tempInstructor);
		appDAO.save(tempInstructor);
		System.out.println("Done!");
	}
}
