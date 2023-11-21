package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Course;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AppDAOImpl implements AppDAO{
    // define field for entity manager

    private EntityManager entityManager;

    // inject entity manager using constructor injection
    @Autowired
    public AppDAOImpl(EntityManager entityManager) {
        this. entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor theInstructor) {
        entityManager.persist(theInstructor);
    }

    @Override
    public Instructor findInstructorById(int theId) {
        return entityManager.find(Instructor.class, theId);
    }

    @Override
    @Transactional
    public void deleteById(int theId) {

        // retrieve the instructor
        Instructor theInstructor = entityManager.find(Instructor.class,theId);

        // Detaching the instructor from courses to avoid exception
        // due to foreign key constraint
        for(Course tempCourse : theInstructor.getCourses()) {
            System.out.println("detaching instructor from course: " + tempCourse.getTitle());
            tempCourse.setInstructor(null);
        }

        // delete the instructor
        entityManager.remove(theInstructor);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int theId) {
        return entityManager.find(InstructorDetail.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int theId) {
        InstructorDetail theInstructorDetail = entityManager.find(InstructorDetail.class, theId);

        // remove the associated object reference
        // break bi-directional link
        theInstructorDetail.getInstructor().setInstructorDetail(null);

        entityManager.remove(theInstructorDetail);
    }

    @Override
    public List<Course> findCoursesByInstructorId(int theId) {
        TypedQuery<Course> query = entityManager.createQuery(
            "from Course where instructor.id = :data",
                Course.class
        );
        query.setParameter("data", theId);

        return query.getResultList();
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int theId) {
        //****TEST NATIVE QUERY******
        //Query testQ = entityManager.createNativeQuery("SELECT id FROM course", Integer.class);
        //var testResult = testQ.getResultList();
        //OR the "warning" option: List<Integer> testResult = testQ.getResultList();
        //System.out.println("**********TEST ID LIST: "+testResult);
        //***************************
        TypedQuery<Instructor> query = entityManager.createQuery(
                "SELECT i FROM Instructor i "
                        +"JOIN FETCH i.courses "
                        +"JOIN FETCH i.instructorDetail "
                        +"WHERE i.id = :data",
                Instructor.class
        );
        query.setParameter("data", theId);
        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void updateInstructor(Instructor theInstructor) {
        entityManager.merge(theInstructor);
    }

    @Override
    public Course findCourseById(int theId) {
        return entityManager.find(Course.class, theId);
    }

    @Override
    @Transactional
    public void update(Course theCourse) {
        entityManager.merge(theCourse);
    }

    @Override
    @Transactional
    public void deleteCourseById(int theId) {
        // retrieve course
        Course theCourse = entityManager.find(Course.class, theId);

        // delete course
        entityManager.remove(theCourse);
    }

}
