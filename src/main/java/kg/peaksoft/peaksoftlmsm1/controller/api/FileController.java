package kg.peaksoft.peaksoftlmsm1.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsm1.db.entity.FilePath;
import kg.peaksoft.peaksoftlmsm1.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static java.net.HttpURLConnection.HTTP_OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/files")
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@Tag(name = "File Api", description = "Files endpoints")
public class FileController {

    private final S3Service s3Service;

    @PreAuthorize("hasAnyAuthority('ROLE_INSTRUCTOR')")
    @Operation(summary = "method create", description = "Only Instructor can create file")
    @PostMapping
    public String upload(@RequestParam("file") MultipartFile file) {
        return s3Service.saveFile(file);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_INSTRUCTOR')")
    @Operation(summary = "method download File", description = "Instructor can download file")
    @GetMapping("download/{id}")
    public ResponseEntity<byte[]> download(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", MediaType.ALL_VALUE);
        headers.add("Content-Disposition", "attachment; filename=" + id);
        byte[] bytes = s3Service.downloadFile(id);
        return ResponseEntity.status(HTTP_OK).headers(headers).body(bytes);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_INSTRUCTOR')")
    @Operation(summary = "method get by id", description = "Instructor can get by id file")
    @GetMapping("{id}")
    public ResponseEntity<FilePath> getById(@PathVariable Long id) {
        return ResponseEntity.ok(s3Service.getById(id));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_INSTRUCTOR')")
    @Operation(summary = "method delete", description = "Only Instructor can delete file")
    @DeleteMapping("{id}")
    public String delete(@PathVariable Long id) {
        return s3Service.deleteFile(id);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_INSTRUCTOR')")
    @Operation(summary = "method get all", description = "Only Instructor can get all file")
    @GetMapping
    public List<String> getAll() {
        return s3Service.listAllFiles();
    }

}