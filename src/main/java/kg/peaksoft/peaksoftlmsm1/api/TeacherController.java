package kg.peaksoft.peaksoftlmsm1.api;


import kg.peaksoft.peaksoftlmsm1.db.dto.teacher.TeacherRequest;
import kg.peaksoft.peaksoftlmsm1.db.dto.teacher.TeacherResponce;
import kg.peaksoft.peaksoftlmsm1.db.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/teacher")
public class TeacherController {

    private final TeacherService teacherService;


    @PostMapping
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<TeacherResponce> create(@RequestBody @Valid TeacherRequest request){
        return new ResponseEntity<>(teacherService.create(request), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<TeacherResponce> update(@PathVariable Long id, @Valid @RequestBody TeacherRequest request){
        TeacherResponce teacherResponse = teacherService.update(id, request);
        return new ResponseEntity<>(teacherResponse, HttpStatus.OK);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<TeacherResponce> getById(@PathVariable Long id) {
        return ResponseEntity.ok(teacherService.getById(id));
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        teacherService.delete(id);
        return new ResponseEntity<>("Teacher deleted successfully.", HttpStatus.OK);
    }
}
