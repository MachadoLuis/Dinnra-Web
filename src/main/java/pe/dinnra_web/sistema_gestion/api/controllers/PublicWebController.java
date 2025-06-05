package pe.dinnra_web.sistema_gestion.api.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.dinnra_web.sistema_gestion.api.model.dto.request.LoginRequest;
import pe.dinnra_web.sistema_gestion.api.model.entity.User;
import pe.dinnra_web.sistema_gestion.api.repository.UserRepository;

@Controller
@RequiredArgsConstructor
@RequestMapping("/public")
public class PublicWebController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/index")
    public String index_page (){
        return "public/index";
    }

    @GetMapping("/success")
    public String succes(){
        return "public/success";
    }

    @GetMapping("/error")
    public String error(){
        return "public/error";
    }

    /*
    @PostMapping("/login-web")
    public String loginWeb (@Valid @ModelAttribute LoginRequest loginRequest){

        User user = userRepository.findByUsername(loginRequest.getUsername());

        if (user != null && passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())){
            return "public/succes";
        }

        return "public/succes";
    }*/

}
