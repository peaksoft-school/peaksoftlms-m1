package kg.peaksoft.peaksoftlmsm1.config;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtil {

    public static void fileUpload(byte[] file, String filePath, String fileName) throws IOException{
        File targetfile = new File(filePath);
        if (targetfile.exists()){
            targetfile.mkdirs();
        }

        FileOutputStream out = new FileOutputStream(filePath + fileName);
        out.write(file);
        out.flush();
        out.close();
    }
}
