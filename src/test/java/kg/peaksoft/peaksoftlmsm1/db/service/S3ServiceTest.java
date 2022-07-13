package kg.peaksoft.peaksoftlmsm1.db.service;

import kg.peaksoft.peaksoftlmsm1.db.service.serviceS3.S3Service;
import org.junit.jupiter.api.Test;
import com.amazonaws.services.s3.AmazonS3;
import kg.peaksoft.peaksoftlmsm1.db.entity.FilePath;
import kg.peaksoft.peaksoftlmsm1.db.repository.FilePathRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.DisplayName.class)
class S3ServiceTest {

    @Autowired
    @InjectMocks
    private S3Service service;
    @Value("${bucketName}")
    private String bucketName;
    @Mock
    private  FilePathRepository repository;
    @Mock
    private  AmazonS3 s3;
    @Mock
    private MultipartFile multipartFile;

    private FilePath filePath;

    @BeforeEach
    public void setup() {

        filePath = new FilePath(1L,"file");
        multipartFile = new MockMultipartFile("file",
                "test contract.pdf",
                MediaType.APPLICATION_PDF_VALUE,
                "<<pdf data>>".getBytes(StandardCharsets.UTF_8));
    }

    @Test
    @Order(1)
    @DisplayName("JUnit test for {1} should save File")
    void saveFile() throws IOException {

        multipartFile = new MockMultipartFile("file",
                "test contract.pdf",
                MediaType.APPLICATION_PDF_VALUE,
                "<<pdf data>>".getBytes(StandardCharsets.UTF_8));

        service.saveFile(multipartFile);
        org.assertj.core.api.Assertions.assertThat(multipartFile.getOriginalFilename()).isEqualTo(multipartFile.getOriginalFilename());
    }

    @Test
    @Order(4)
    @DisplayName("JUnit test for {4} delete File by id")
    void deleteFile() {

        s3.deleteObject(bucketName, filePath.getFileName());
        repository.delete(filePath);
    }

    @Test
    @Order(3)
    @DisplayName("JUnit test for {3} should find File by id")
    void getById() {

        when(repository.findById(1L)).thenReturn(Optional.of(filePath));
        service.getById(1L);
    }

    @AfterEach
    public void tearDown() {
        filePath = null;
        multipartFile = null;
    }
}