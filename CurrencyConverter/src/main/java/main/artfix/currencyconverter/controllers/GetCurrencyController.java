package main.artfix.currencyconverter.controllers;

import lombok.RequiredArgsConstructor;
import main.artfix.currencyconverter.service.CurrencyGetService;
import main.artfix.currencyconverter.service.MainPageBanksService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class GetCurrencyController {

    private final CurrencyGetService currencyGetService;
    private final MainPageBanksService mainPageBanksService;

    @RequestMapping("/user-get-currency")
    public String convertProcess(@RequestParam("form-from-currency") String FormFromCurrency,
                                 @RequestParam("form-to-currency") String FormToCurrency,
                                 @RequestParam("form-bank-currency") String FormBankCurrency,
                                 @RequestParam("form-amount-currency") int FormAmountCurrency, Model model) {
        String currencyResult = currencyGetService.getCurrency(FormFromCurrency, FormToCurrency, FormAmountCurrency,
                FormBankCurrency);
        model.addAttribute("banksOptions", mainPageBanksService.getBanks());
        model.addAttribute("homemessage", currencyResult);
        return "home";
    }
}
