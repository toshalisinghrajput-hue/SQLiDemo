package com.rexael.sqlidemo.controller;
import java.sql.Timestamp;
import com.rexael.sqlidemo.model.User;
import com.rexael.sqlidemo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;


@Controller
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }


    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user) {

        userService.registerUser(user);

        return "redirect:/login";
    }


    @PostMapping("/login")
    public String loginUser(
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String mode,
            HttpSession session,
            Model model) {

        User user = null;

        if ("secure".equalsIgnoreCase(mode)) {

            user = userService.loginUser(email, password);

        } else if ("vulnerable".equalsIgnoreCase(mode)) {

            user = userService.vulnerableLogin(email, password);
        } else{
            model.addAttribute("error","Please selct a login mode.");
            return "login";
        }

        if (user == null) {
            model.addAttribute("error", "Invalid login credentials.");
            return "login";
        }
        user.setLastLogin(new Timestamp(System.currentTimeMillis()));
        user.setLessonsCompleted(0);
        user.setPracticeAttempts(0);
        user.setSecurityScore(0);

        session.setAttribute("user", user);
        session.setAttribute("mode", mode);

        return "redirect:/dashboard";
    }
    @GetMapping("/dashboard")
    public String showDashboard(HttpSession session, Model model) {

        User user = (User) session.getAttribute("user");
        String mode = (String) session.getAttribute("mode");

        // If the user is not logged in, send them back to login
        if (user == null) {
            return "redirect:/login";
        }

        // Send user information to Thymeleaf
        model.addAttribute("user", user);
        model.addAttribute("mode", mode);

        return "sqldash";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @PostMapping("/logout")
    public String logoutUser(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
