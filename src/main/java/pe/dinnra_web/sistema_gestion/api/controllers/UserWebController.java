package pe.dinnra_web.sistema_gestion.api.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usuario")

public class UserWebController {
    @GetMapping("/usuario_dejar_review")
    public String dejar_review(){
        return "usuario/dejar_review";
    }

    @GetMapping("/usuario_libro_reclamaciones")
    public String libro_reclamaciones(){
        return "usuario/libro_reclamaciones";
    }

    @GetMapping("/usuario_mis_reservas")
    public String mis_reservas(){
        return "usuario/mis_reservas";
    }

    @GetMapping("/usuario_mis_solicitudes_ayuda")
    public String mis_solicitudes_ayuda(){
        return "usuario/mis_solicitudes_ayuda";
    }

    @GetMapping("/usuario_perfil_usuario")
    public String perfil_usuario(){
        return "usuario/perfil_usuario";
    }

    @GetMapping("/usuario_reservar_habitacion")
    public String reservar_habitacion(){
        return "usuario/reservar_habitacion";
    }

    @GetMapping("/usuario_sugerencias")
    public String sugerencias(){
        return "usuario/sugerencias";
    }

    @GetMapping("/usuario_ver_habitaciones")
    public String ver_habitaciones(){
        return "usuario/ver_habitaciones";
    }
}

