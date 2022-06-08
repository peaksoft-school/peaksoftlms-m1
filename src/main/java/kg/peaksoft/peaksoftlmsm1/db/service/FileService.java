package kg.peaksoft.peaksoftlmsm1.db.service;

import org.springframework.web.multipart.MultipartFile;
import java.util.List;

public interface FileService {

     String saveFile(MultipartFile file);

     byte[] downloadFile(Long id);

     String deleteFile(Long id);

     List<String> listAllFiles();
}
