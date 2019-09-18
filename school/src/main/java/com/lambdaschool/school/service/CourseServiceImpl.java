package com.lambdaschool.school.service;

import com.lambdaschool.school.model.Course;
import com.lambdaschool.school.model.Student;
import com.lambdaschool.school.repository.CourseRepository;
import com.lambdaschool.school.view.CountStudentsInCourses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;

@Service(value = "courseService")
public class CourseServiceImpl implements CourseService
{
    @Autowired
    private CourseRepository courserepos;

    @Override
    public ArrayList<Course> findAll()
    {
        ArrayList<Course> list = new ArrayList<>();
        courserepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public ArrayList<CountStudentsInCourses> getCountStudentsInCourse()
    {
        return courserepos.getCountStudentsInCourse();
    }

    @Transactional
    @Override
    public void delete(long id) throws EntityNotFoundException
    {
        if (courserepos.findById(id).isPresent())
        {
            courserepos.deleteCourseFromStudcourses(id);
            courserepos.deleteById(id);
        } else
        {
            throw new EntityNotFoundException(Long.toString(id));
        }
    }

    @Override
    public Course findCourseById(long id) throws EntityNotFoundException
    {
        return courserepos.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));

    }

    @Transactional
    @Override
    public Course save(Course course)
    {
        Course newCourse = new Course();

        newCourse.setCoursename(course.getCoursename());
        newCourse.setInstructor(course.getInstructor());

//        ArrayList<Student> newStudent = new ArrayList<>();
//        for (Student s : course.getStudents())
//        {
//            newStudent.add(new Student(s.getStudname()));
//        }
//        newCourse.setStudents(newStudent);

        return courserepos.save(newCourse);
    }
}
