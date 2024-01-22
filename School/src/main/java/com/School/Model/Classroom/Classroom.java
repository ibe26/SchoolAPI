package com.School.Model.Classroom;

import com.School.Model.Course.Course;
import com.School.Model.Student.Student;
import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "Classroom")
public class Classroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "classroom_id")
    private Long id;
    private String name;
    @OneToMany
    private Collection<Course> courses;
    @OneToMany
    private  Collection<Student> students;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Course> getCourses() {
        return courses;
    }

    public void setCourses(Collection<Course> courses) {
        this.courses = courses;
    }

    public Collection<Student> getStudents() {
        return students;
    }

    public void setStudents(Collection<Student> students) {
        this.students = students;
    }
}
