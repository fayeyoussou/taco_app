package sn.youdev.controller;
import org.springframework.security.crypto.password.PasswordEncoder; import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sn.youdev.dto.RegistrationForm;
import sn.youdev.repository.UserRepository;

@Controller
@RequestMapping("/register")
public class RegistrationController {
    private UserRepository userRepo;
    private PasswordEncoder passwordEncoder;

    public RegistrationController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @GetMapping
    public String registerForm(Model model) {
        model.addAttribute("title", "Fill in order");
        model.addAttribute("content", "registration");
        int sum = 143;
        for (int i = 20; i < 31; i++) {
            System.out.println(i +" -> "+sum);
            sum+=18;
        }
        return "layout";
    }
    @PostMapping
    public String processRegistration(RegistrationForm form) {

        userRepo.save(form.toUser(passwordEncoder));
        return "redirect:/login";
    }
}
