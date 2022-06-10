package kg.peaksoft.peaksoftlmsm1.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsm1.db.entity.models.FilePath;
import kg.peaksoft.peaksoftlmsm1.db.service.FilePathService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("api/files")
@Tag(name = "File controller", description = "Instructor can create, update, and delete")
public class FileController {

    private final FilePathService filePathService;

    @ResponseBody
    @Operation(summary = "method create file", description = "instructor can upload file")
    @PreAuthorize("hasAnyAuthority('ROLE_INSTRUCTOR')")
    @PostMapping
    public String upload(@RequestParam("file") MultipartFile file){
        return filePathService.upload(file);
    }

    @Operation(summary = "method delete file", description = "instructor can delete file")
    @PreAuthorize("hasAnyAuthority('ROLE_INSTRUCTOR')")
    @DeleteMapping("{id}")
    public ResponseEntity<FilePath> delete(@PathVariable Long id) {
        filePathService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "method get file by id ", description = "instructor can found get by id")
    @PreAuthorize("hasAnyAuthority('ROLE_INSTRUCTOR')")
    @GetMapping("{id}")
    public ResponseEntity<FilePath> getById(@PathVariable Long id) {
        return ResponseEntity.ok(filePathService.getById(id).get());
    }

    @Operation(summary = "method get all files", description = "Instructor can get all files")
    @PreAuthorize("hasAnyAuthority('ROLE_INSTRUCTOR')")
    @GetMapping
    public ResponseEntity<List<FilePath>> getAll() {
        return ResponseEntity.ok(filePathService.getAllFile());
    }

}
