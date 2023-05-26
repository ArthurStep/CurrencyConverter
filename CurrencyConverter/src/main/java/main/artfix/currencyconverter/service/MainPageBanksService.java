package main.artfix.currencyconverter.service;

import lombok.RequiredArgsConstructor;
import main.artfix.currencyconverter.models.Course;
import main.artfix.currencyconverter.repos.CoursesRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MainPageBanksService {

    private final CoursesRepo coursesRepo;

    public String getBanks() {
        List<String> bankNames = new ArrayList<>();
        Iterable<Course> courses = coursesRepo.findAll();
        for (Course course : courses) {
            String bank = course.getBank();
            if (bank != null && !bankNames.contains(bank)) {
                bankNames.add(bank);
            }
        }
        StringBuilder banksStringBuilder = new StringBuilder();
        banksStringBuilder.append("<option value=\"global\">Global</option>\n");
        for (String bankName : bankNames) {
            banksStringBuilder.append("<option value=\"").append(bankName).append("\">").append(bankName).append("</option>\n");
        }
        return banksStringBuilder.toString();
    }
}
