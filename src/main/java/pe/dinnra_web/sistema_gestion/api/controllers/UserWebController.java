package pe.dinnra_web.sistema_gestion.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.dinnra_web.sistema_gestion.api.model.dto.response.RoomResponse;
import pe.dinnra_web.sistema_gestion.api.service.impl.RoomServiceImpl;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/usuario")

public class UserWebController {

    @Autowired
    private RoomServiceImpl roomService;

    @GetMapping("/review")
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

    @GetMapping("/rooms")
    public String ver_habitaciones(Model model){
        List<RoomResponse> rooms = roomService.findAll();
        model.addAttribute("rooms", rooms);
        return "usuario/habitaciones";
    }
}

