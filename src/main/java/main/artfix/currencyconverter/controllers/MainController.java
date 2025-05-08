package main.artfix.currencyconverter.controllers;

import lombok.RequiredArgsConstructor;
import main.artfix.currencyconverter.models.Course;
import main.artfix.currencyconverter.repos.CoursesRepo;
import main.artfix.currencyconverter.service.AdminService;
import main.artfix.currencyconverter.service.MainPageBanksService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final AdminService adminService;
    private final MainPageBanksService mainPageBanksService;

    @GetMapping("/")
    public String indexPage(Model model) {
        model.addAttribute("banksOptions", mainPageBanksService.getBanks());
        return "home";
    }

    @GetMapping("/admin")
    public String adminPage() {
        adminService.sendAdminCode();
        return "adminlogin";
    }
}
