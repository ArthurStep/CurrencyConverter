package main.artfix.currencyconverter.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AppMailSender appMailSender;
    private final RandomCodeService randomCodeService;
    public String generatedAdminCode = "WrongCode";

    @Value("${app.admin.name}")
    private String AdminName;

    @Value("${app.admin.mail}")
    private String AdminMail;

    public void sendAdminCode() {
        generatedAdminCode = randomCodeService.getCode();
        appMailSender.sendMail(AdminMail, "CurrencyConverter Verification Code: ******",
                "Hi, Dear " + AdminName + " Your Verification Code For CurrencyConverter is: " +
                        generatedAdminCode + ".");
    }

}
