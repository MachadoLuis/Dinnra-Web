package pe.dinnra_web.sistema_gestion.api.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/empleado")
public class EmployeeWebController {

    @GetMapping("/emp_dashboard")
    public String dashboard_empleado(){
        return "empleado/dashboard_empleado";
    }

    @GetMapping("/emp_gestionar_ayuda_problemas")
    public String manage_employeers_problems(){
        return "empleado/gestionar_ayuda_problemas";
    }

    @GetMapping("/emp_gestionar_servicios")
    public String manage_services(){
        return "empleado/gestionar_servicios";
    }

}