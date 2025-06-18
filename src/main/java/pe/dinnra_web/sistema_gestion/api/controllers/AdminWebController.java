package pe.dinnra_web.sistema_gestion.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.dinnra_web.sistema_gestion.api.model.dto.response.EmployeeResponse;
import pe.dinnra_web.sistema_gestion.api.model.dto.response.PositionResponse;
import pe.dinnra_web.sistema_gestion.api.service.impl.EmployeeServiceImpl;
import pe.dinnra_web.sistema_gestion.api.service.impl.PositionServiceImpl;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminWebController {

    @Autowired
    private EmployeeServiceImpl employeeService;

    @Autowired
    private PositionServiceImpl positionService;

    @GetMapping("/dash_admin")
    public String da_admin(){
        return "admin/dashboard";
    }

    @GetMapping("/crea_cliente")
    public String creare_cliente(){return "admin/crear_cliente";}

    @GetMapping("/employee")
    public String empleados (Model model){
        List<EmployeeResponse> employees = employeeService.findAll();
        List<PositionResponse> positions = positionService.findAllEmployedPositions();
        model.addAttribute("employees", employees );
        model.addAttribute("positions", positions);
        return "admin/empleados";
    }

    @GetMapping("/position")
    public String position(Model model) {
        List<PositionResponse> positions = positionService.findAll();
        model.addAttribute("positions", positions);
        return "admin/position";
    }

    @GetMapping("/crea_habitacion")
    public String creare_habitacion(){return "admin/crear_habitacion";}
    @GetMapping("/crea_posicion")
    public String creare_posicion(){return "admin/crear_posicion";}

}
