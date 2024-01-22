package com.School.Model.Student;

import com.School.Model.Course.Course;

import java.util.Collection;

public record StudentDto(String firstName, String lastName, Collection<Course> courses) {
}