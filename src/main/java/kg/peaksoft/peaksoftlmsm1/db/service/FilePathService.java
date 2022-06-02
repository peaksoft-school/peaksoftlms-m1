package kg.peaksoft.peaksoftlmsm1.db.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import kg.peaksoft.peaksoftlmsm1.config.FileUtil;
import kg.peaksoft.peaksoftlmsm1.db.entity.models.FilePath;
import kg.peaksoft.peaksoftlmsm1.db.repository.FilePathRepository;
import kg.peaksoft.peaksoftlmsm1.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class FilePathService {

    private final FilePathRepository repository;

    public String upload(@RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            String path = ClassUtils.getDefaultClassLoader().getResource("static")+"";
            try {
                FileUtil.fileUpload(file.getBytes(), path, fileName);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            FilePath biaopath = new FilePath();
            biaopath.setPath("http://localhost:8080/" + fileName);
            repository.save(biaopath);
        }
        return "successfully added:  " + file.getName();
    }

    public FilePath delete(Long id) {
        FilePath filePath = repository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Entity", "id", id));
        repository.deleteById(id);
        return filePath;
    }

    public Optional<FilePath> getById(Long id) {
        Optional<FilePath> filePath = repository.findById(id);
        if (filePath.isEmpty()) {
            System.out.println(filePath + "with id not found");
        }
        return filePath;
    }

    public List<FilePath> getAllFile(){
        return (List<FilePath>) repository.findAll();
    }
}