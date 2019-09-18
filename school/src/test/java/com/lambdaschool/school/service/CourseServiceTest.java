package com.lambdaschool.school.service;

import com.lambdaschool.school.SchoolApplication;
import com.lambdaschool.school.model.Course;
import com.lambdaschool.school.model.Instructor;
import com.lambdaschool.school.model.Student;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SchoolApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CourseServiceTest
{

    @Autowired
    private CourseService courseService;
    @Autowired
    InstructorService instructorService;


    @Before
    public void setUp() throws Exception
    {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws Exception
    {
    }

    @Test
    public void findAll()
    {
        assertEquals(6, courseService.findAll().size());
    }

    @Test
    public void deleteFound()
    {
        courseService.delete(6);
        assertEquals(5, courseService.findAll().size());
    }
    @Test(expected = EntityNotFoundException.class)
    public void  deleteNotFound()
    {
        courseService.delete(77);
        assertEquals(5, courseService.findAll().size());
    }

    @Test
    public void findCourseById()
    {
        assertEquals("Data Science", courseService.findCourseById(1).getCoursename());
    }

    @Test
    public void save()
    {
      Instructor sally = instructorService.getInstructorById(1);
      Course c8 = new Course("Algebra2", sally);
      Course addCourse = courseService.save(c8);
      assertNotNull(addCourse);
      Course foundCourse = courseService.findCourseById(addCourse.getCourseid());
      assertEquals(addCourse.getCoursename(), foundCourse.getCoursename());
    }


}
