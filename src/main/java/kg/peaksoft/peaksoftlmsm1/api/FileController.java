package kg.peaksoft.peaksoftlmsm1.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsm1.db.entity.FilePath;
import kg.peaksoft.peaksoftlmsm1.service.S3.S3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import static java.net.HttpURLConnection.HTTP_OK;

@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@Tag(name = "File controller", description = "INSTRUCTOR can create, update and delete")
@RequestMapping("api/teachers/files")
public class FileController {

    private final S3Service s3Service;

    @PreAuthorize("hasAnyAuthority('ROLE_INSTRUCTOR')")
    @Operation(summary = "method create", description = "Only Instructor can create file")
    @PostMapping
    public String upload(@RequestParam("file") MultipartFile file){
        log.info("inside FileController upload method");
        return s3Service.saveFile(file);
    }

    /**
     *  Метод download загружает файл из сервера и показывает в Postman
     */
    @PreAuthorize("hasAnyAuthority('ROLE_INSTRUCTOR')")
    @Operation(summary = "method download File", description = "Instructor can download file")
    @GetMapping("download/{id}")
    public ResponseEntity<byte[]> download(@PathVariable Long id){
        HttpHeaders headers=new HttpHeaders();
        headers.add("Content-type", MediaType.ALL_VALUE);
        headers.add("Content-Disposition", "attachment; filename="+id);
        byte[] bytes = s3Service.downloadFile(id);
        log.info("inside FileController download method");
        return  ResponseEntity.status(HTTP_OK).headers(headers).body(bytes);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_INSTRUCTOR')")
    @Operation(summary = "method get by id", description = "Instructor can get by id file")
    @GetMapping("{id}")
    public ResponseEntity<FilePath> getById(@PathVariable Long id) {
        log.info("inside FileController get By Id method");
        return ResponseEntity.ok(s3Service.getById(id));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_INSTRUCTOR')")
    @Operation(summary = "method delete", description = "Only Instructor can delete file")
    @DeleteMapping("{id}")
    public  String delete(@PathVariable Long id){
        log.info("inside FileController delete method");
        return s3Service.deleteFile(id);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_INSTRUCTOR')")
    @Operation(summary = "method get all", description = "Only Instructor can get all file")
    @GetMapping
    public List<String> getAll(){
        log.info("inside FileController get all method");
        return s3Service.listAllFiles();
    }

}