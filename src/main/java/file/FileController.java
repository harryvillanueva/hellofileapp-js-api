package file;



import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;



@RestController
@CrossOrigin(origins = "*")
public class FileController {

    private final StorageService storageService;

    public FileController(StorageService storageService) {
        this.storageService = storageService;
    }


    @PostMapping("/")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {

        boolean success = storageService.store(file);

        if (success) {
            // Devuelve un 200 OK con un mensaje de éxito en el cuerpo
            String message = "¡Fichero subido con éxito: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } else {
            // Devuelve un 400 Bad Request (o 500) con un mensaje de error
            String message = "Error al subir el fichero: " + file.getOriginalFilename() + ". (¿Quizás estaba vacío?)";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
    }
}
