package pe.dinnra_web.sistema_gestion.api.controllers.RestControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/img-room")
//Resource y resourceLoader maneja rutas relativas de la aplicacion de spring, no absolutas;

public class ImgController {

    private static final String carpetaPath = System.getProperty("user.dir") + "/src/main/resources/static/img/imgRoom/";

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private ResourcePatternResolver resourcePatternResolver;

    @PostMapping
    public ResponseEntity<String> uploadImg (@RequestParam("img") MultipartFile file) throws IOException {

        //Obtiene la ruta absoluta de la carpeta resources del proyecto Spring
        Path resourcePath = Paths.get(carpetaPath);

        /* Una vez definida la ruta absoluta de la carpeta donde estan nuestras imagenes
         debemos definir la ruta a la cual el archivo debe ir dentro de la ruta absoluta /nombreArchivo */
        Path filePath = resourcePath.resolve(file.getOriginalFilename());

        //Escribimos la imagen con sus bytes y las colocamos dentro de la ruta a la cual nuestro archivo debe ir
        Files.write(filePath, file.getBytes());

        return ResponseEntity.ok("Se subieron los archivos");
    }

    @GetMapping
    public ResponseEntity<List<Map<String, String>>> images() throws IOException {
        List<Map<String, String>> names = new ArrayList<>();
        Resource[] resources = resourcePatternResolver.getResources("classpath:static/img/imgRoom/*.{jpg, png}");

        for (Resource resource: resources){
            Map<String, String> fileInfo = new HashMap<>();
            fileInfo.put("name", resource.getFilename());
            names.add(fileInfo);
        }
        return ResponseEntity.ok(names);
    }

}
