package com.school.controller.classroom;

import com.school.controller.RequestMappingHelper.Routes;
import com.school.dto.classroom.ClassroomDto;
import com.school.model.classroom.Classroom;
import com.school.service.interfaces.classroom.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @GetMapping(Routes.getAll)
    public ResponseEntity<Collection<Classroom>> getClassrooms(){
        return new ResponseEntity<>(classroomService.getClassrooms(), HttpStatus.OK);
    }
    @GetMapping(Routes.get)
    public ResponseEntity<?> getClassroomById(@PathVariable("id") Long id){
        final Optional<Classroom> classroomOptional=classroomService.getClassroomById(id);
        return classroomOptional.isPresent() ? new ResponseEntity<>(classroomOptional.get(),HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping(Routes.save)
    public ResponseEntity<?> saveClassroom(@RequestBody() ClassroomDto classroomDto){
        Optional<Classroom> classroomOptional=classroomService.addClassroom(classroomDto);
        return classroomOptional.isPresent() ? new ResponseEntity<>(classroomOptional.get(),HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @DeleteMapping(Routes.delete)
    public ResponseEntity<?> deleteClassroom(@PathVariable("id")Long id){
        return  classroomService.deleteClassroom(id)!=null ? new ResponseEntity<>(id,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @PutMapping(Routes.update)
    public ResponseEntity<?> updateClassroom(@PathVariable("id") Long id, @RequestBody ClassroomDto classroomDto){
        Optional<Classroom> classroomOptional=classroomService.updateClassroom(classroomDto,id);
        return classroomOptional.isPresent() ? new ResponseEntity<>(classroomOptional.get(),HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
