package com.School.Model.Classroom;

import jakarta.persistence.*;
import org.hibernate.annotations.Formula;


@Entity
@Table(name = "Classroom")
public class Classroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "classroom_id")
    private Long id;
    private String name;
    @Formula(value = "(SELECT COUNT(*) FROM (SELECT * FROM Student as s\n" +
            "JOIN student_course as sc\n" +
            "ON sc.student_id=s.student_id) as result\n" +
            "JOIN course as c\n" +
            "ON result.course_id=c.course_id\n" +
            "where c.classroom_id=classroom_id)")
    private Long studentCount;

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


    public Long getStudentCount() {
        return studentCount;
    }

    public void setStudents(Long studentCount) {
        this.studentCount = studentCount;
    }
}
