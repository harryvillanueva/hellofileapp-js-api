package file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class HelloFileService implements StorageService {


    private final Path rootLocation;


    @Autowired
    public HelloFileService(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
    }




    @Override
    public void init() {

        try{

            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            System.out.println("no se pudo inicializar fileSystem");
        }
    }

    @Override
    public boolean store(MultipartFile file) {

        try {
            if (file.isEmpty()) {
                System.out.println("el fichero esta vacio");
                return false;
            }
            Path destinationFile = this.rootLocation.resolve(
                            Paths.get(file.getOriginalFilename()))
                    .normalize().toAbsolutePath();
            if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
                // This is a security check
                System.out.println(
                        "Cannot store file outside current directory.");
                return false;
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile,
                        StandardCopyOption.REPLACE_EXISTING);
                return true;

            }
        }
        catch (IOException e) {
            System.out.println("Failed to store file.");
            return false;
        }

    }
}
