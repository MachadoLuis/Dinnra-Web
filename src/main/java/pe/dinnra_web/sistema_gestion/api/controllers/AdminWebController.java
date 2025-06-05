package pe.dinnra_web.sistema_gestion.api.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminWebController {

    @GetMapping("/dash_admin")
    public String da_admin(){
        return "admin/dashboard";
    }


    @GetMapping("/crea_cliente")
    public String creare_cliente(){return "admin/crear_cliente";}
    @GetMapping("/crea_empleado")
    public String creare_empleado(){return "admin/crear_empleado";}
    @GetMapping("/crea_habitacion")
    public String creare_habitacion(){return "admin/crear_habitacion";}
    @GetMapping("/crea_posicion")
    public String creare_posicion(){return "admin/crear_posicion";}

}
