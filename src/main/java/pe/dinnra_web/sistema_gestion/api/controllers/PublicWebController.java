package pe.dinnra_web.sistema_gestion.api.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
/* LA RAIZ DEL PROYECTO SERIA http://localhost:8080/public/PAGINA QUE QUIERAS VER */
@RequestMapping("/public")
public class PublicWebController {

    /* POR CADA HTML QUE TENGAMOS ESTE DEBE TENER UN ENLACE ESTE ES UNO DE PRUEBA
     * PARA PODER COMPROBAR SI FUNCIONA TIENEN QUE ENTRAR A http://localhost:8080/public/index */
    @GetMapping("/index")
    public String indexPrueba(){
        return "public/index";
    }

    /* SI TUVIERAN UN HTML LLAMADO PRUEBA EN EL MISMO NIVEL QUE HTML TENDRIAN QUE HACER ESTO */
    @GetMapping("/contacto")
    public String contacto(){
        return "public/contacto";
    }

    /* SI TUVIERAN UN HTML UN NIVEL DENTRO SE VERIA ASI */
    @GetMapping("/NIVELDENTRO")
    public String dentro(){
        return "carpeta/unniveldentro";
    }
}
