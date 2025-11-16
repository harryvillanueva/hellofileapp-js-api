package file;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

    void init();
    boolean store(MultipartFile file);
}
