package com.School.Service.Interface;

import com.School.Model.Classroom.Classroom;
import com.School.Model.Classroom.ClassroomDto;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface ClassroomService {
    Collection<Classroom> getClassrooms();
    Classroom addClassroom(ClassroomDto ClassroomDto);
    Long deleteClassroom(Long id);
    Classroom updateClassroom(ClassroomDto ClassroomDto,Long id);
}
