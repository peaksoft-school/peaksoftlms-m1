package kg.peaksoft.peaksoftlmsm1.db.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazonaws.util.IOUtils;
import kg.peaksoft.peaksoftlmsm1.db.entity.models.FilePath;
import kg.peaksoft.peaksoftlmsm1.db.repository.FilePathRepository;
import kg.peaksoft.peaksoftlmsm1.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class S3Service implements FileService{

    @Value("${bucketName}")
    private String bucketName;
    private final FilePathRepository repository;
    private final AmazonS3 s3;

    @Override
    public String saveFile(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        try {
            FilePath filePath = new FilePath(originalFilename);
            repository.save(filePath);
            File file1 = convertMultiPartToFile(file);
            s3.putObject(bucketName, originalFilename, file1);
            return "id: "+filePath.getId() + "  fileName: " + filePath.getFileName();
        } catch (IOException e) {
            throw  new RuntimeException(e);
        }
    }

    @Override
    public byte[] downloadFile(Long id) {
        FilePath filePath = repository.findById(id).get();
        S3Object object = s3.getObject(bucketName, filePath.getFileName());
        S3ObjectInputStream objectContent = object.getObjectContent();
        try {
            return IOUtils.toByteArray(objectContent);
        } catch (IOException e) {
            throw  new RuntimeException(e);
        }
    }

    @Override
    public String deleteFile(Long id) {
        FilePath filePath = repository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Entity", "id", id));
        s3.deleteObject(bucketName, filePath.getFileName());
        repository.delete(filePath);
        return "File successfully deleted";
    }

    @Override
    public List<String> listAllFiles() {
        ListObjectsV2Result listObjectsV2Result = s3.listObjectsV2(bucketName);
        return  listObjectsV2Result.getObjectSummaries().stream()
                .map(S3ObjectSummary::getKey).collect(Collectors.toList());
    }

    public FilePath getById(Long id){
        Optional<FilePath> filePath = repository.findById(id);
        if(filePath.isEmpty()){
            System.out.println(filePath + "with id not found");
        }
        return repository.save(filePath.get());
    }

    private File convertMultiPartToFile(MultipartFile file ) throws IOException {
        File convFile = new File( file.getOriginalFilename() );
        FileOutputStream fos = new FileOutputStream( convFile );
        fos.write( file.getBytes() );
        fos.close();
        return convFile;
    }

}