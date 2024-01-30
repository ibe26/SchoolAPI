package com.school.service.interfaces;

import com.school.model.classroom.Classroom;
import com.school.dto.classroom.ClassroomDto;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public interface ClassroomService {
    Collection<Classroom> getClassrooms();
    Optional<Classroom> getClassroomById(Long id);
    Optional<Classroom> addClassroom(ClassroomDto classroomDto);
    Long deleteClassroom(Long id);
    Optional<Classroom> updateClassroom(ClassroomDto classroomDto,Long id);
}
