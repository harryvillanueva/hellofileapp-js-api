package file;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class FileController {

    private final StorageService storageService;

    public FileController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/")
    public String showUploadForm() {
        // Devuelve el nombre del archivo HTML (sin .html)
        // Spring Boot lo buscará en 'src/main/resources/templates/uploadForm.html'
        return "index";
    }

    @PostMapping("/")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {

        boolean success = storageService.store(file);

        if (success){
            redirectAttributes.addFlashAttribute("successMessage",
                    "¡Fichero subido con éxito: " + file.getOriginalFilename() + "!");
        } else {
            // 5. Añadir mensaje de error
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Error al subir el fichero: " + file.getOriginalFilename() + ". (¿Quizás estaba vacío?)");
        }


        return "redirect:/";
    }
}
