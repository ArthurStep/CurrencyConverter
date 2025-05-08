package main.artfix.currencyconverter.controllers;

import lombok.RequiredArgsConstructor;
import main.artfix.currencyconverter.service.AdminService;
import main.artfix.currencyconverter.service.CurrencyGetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/process")
@RequiredArgsConstructor
public class AdminProcessController {

    private final AdminService adminService;
    private final CurrencyGetService currencyGetService;


    @RequestMapping("/admin")
    public String processAdminPage(@RequestParam(value = "form-admin-login-mail-code", required = false, defaultValue = "")
                                   String formAdminLoginMailCode, Model model) {
        if (formAdminLoginMailCode.equals(adminService.generatedAdminCode)) {
            return "admin";
        } else {
            model.addAttribute("adminloginmessage", "Wrong Login Code!");
            return "adminlogin";
        }
    }

    @RequestMapping("/admin-write")
    public String processWriteAdminCurrency(@RequestParam("form-from-currency-admin") String formFromCurrency,
                                            @RequestParam("form-to-currency-admin") String formToCurrency,
                                            @RequestParam("form-amount-currency-admin") int formAmountCurrency,
                                            @RequestParam("form-bank-currency-admin") String formBankCurrency,
                                            @RequestParam("form-write-admin-mail-code") String formWriteAdminMailCode,
                                            Model model) {
        if (formWriteAdminMailCode.equals(adminService.generatedAdminCode)) {
            currencyGetService.changeCurrency(formFromCurrency, formToCurrency, formAmountCurrency, formBankCurrency);
            model.addAttribute("adminmessage", "Success written!");
            return "admin";
        } else {
            model.addAttribute("adminloginmessage", "Wrong admin password!");
            return "adminlogin";
        }
    }
}
