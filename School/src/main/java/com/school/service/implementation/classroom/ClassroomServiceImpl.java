package com.school.service.implementation.classroom;

import com.school.model.classroom.Classroom;
import com.school.dto.classroom.ClassroomDto;
import com.school.repository.classroom.ClassroomRepository;
import com.school.service.interfaces.classroom.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class ClassroomServiceImpl implements ClassroomService {
    private final ClassroomRepository classroomRepository;

    @Autowired
    public ClassroomServiceImpl(ClassroomRepository classroomRepository) {
        this.classroomRepository = classroomRepository;
    }

    @Override
    public Collection<Classroom> getClassrooms() {
        return classroomRepository.findAll();
    }

    @Override
    public Optional<Classroom> getClassroomById(Long id) {
        return classroomRepository.existsById(id) ? classroomRepository.findById(id) : Optional.empty();
    }

    @Override
    public Optional<Classroom> addClassroom(ClassroomDto classroomDto) {
        if(classroomDto!=null){
            Classroom classroom=new Classroom();
            classroom.setName(classroomDto.name());
            return Optional.of(classroomRepository.save(classroom));
        }
        return Optional.empty();
    }

    @Override
    public Long deleteClassroom(Long id) {
        if(classroomRepository.existsById(id)){
            classroomRepository.deleteById(id);
            return id;
        }
        return null;
    }

    @Override
    public Optional<Classroom> updateClassroom(ClassroomDto classroomDto, Long id) {
        if(classroomRepository.findById(id).isPresent() && classroomDto!=null){
            Classroom classroom=classroomRepository.findById(id).get();
            classroom.setName(classroomDto.name());
            return Optional.of(classroomRepository.save(classroom));
        }
        return Optional.empty();
    }
}
