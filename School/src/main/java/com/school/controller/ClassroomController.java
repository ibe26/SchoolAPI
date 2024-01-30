package com.school.controller;

import com.school.controller.RequestMappingHelper.Routes;
import com.school.model.classroom.Classroom;
import com.school.dto.classroom.ClassroomDto;
import com.school.service.interfaces.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("api/classroom")
public class ClassroomController {
    private final ClassroomService classroomService;
    @Autowired
    public ClassroomController(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }
    @Cacheable("classroom")
    @GetMapping(Routes.getAll)
    public ResponseEntity<Collection<Classroom>> getClassrooms(){
        return new ResponseEntity<>(classroomService.getClassrooms(), HttpStatus.OK);
    }
    @Cacheable(value = "classroom",key = "#id")
    @GetMapping(Routes.get)
    public ResponseEntity<?> getClassroomById(@PathVariable("id") Long id){
        final Optional<Classroom> classroomOptional=classroomService.getClassroomById(id);
        return classroomOptional.isPresent() ? new ResponseEntity<>(classroomOptional.get(),HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @CacheEvict(cacheNames = "classroom",allEntries = true)
    @PostMapping(Routes.save)
    public ResponseEntity<?> saveClassroom(@RequestBody() ClassroomDto classroomDto){
        Optional<Classroom> classroomOptional=classroomService.addClassroom(classroomDto);
        return classroomOptional.isPresent() ? new ResponseEntity<>(classroomOptional.get(),HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @CacheEvict(cacheNames = "classroom",key = "#id")
    @DeleteMapping(Routes.delete)
    public ResponseEntity<?> deleteClassroom(@PathVariable("id")Long id){
        return  classroomService.deleteClassroom(id)!=null ? new ResponseEntity<>(id,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @CachePut(cacheNames = "classroom",key = "#id")
    @PutMapping(Routes.update)
    public ResponseEntity<?> updateClassroom(@PathVariable("id") Long id, @RequestBody ClassroomDto classroomDto){
        Optional<Classroom> classroomOptional=classroomService.updateClassroom(classroomDto,id);
        return classroomOptional.isPresent() ? new ResponseEntity<>(classroomOptional.get(),HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
