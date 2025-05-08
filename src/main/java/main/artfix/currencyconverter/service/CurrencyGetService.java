package main.artfix.currencyconverter.service;

import lombok.RequiredArgsConstructor;
import main.artfix.currencyconverter.models.Course;
import main.artfix.currencyconverter.repos.CoursesRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CurrencyGetService {

    private final CoursesRepo coursesRepo;
    private final CurrencyApiService currencyApiService;

    public String getCurrency(String from, String to, int amount, String bank) {
        String amountResult;
        if (!bank.equals("global")) {
            try {
                Course coursesGetByFrom = coursesRepo.findByFromCurrencyAndBank(from, bank);
                String dbGetTo = coursesGetByFrom.getToCurrency();
                int dbGetAmount = coursesGetByFrom.getHowCurrency();

                amountResult = amount + " " + from + " = " + dbGetAmount * amount + " " + dbGetTo;

            } catch (Exception e) {
                amountResult = currencyApiService.get(from, to, amount) + "Bank unavailable info from global!";
            }
        } else {
            amountResult = currencyApiService.get(from, to, amount);
        }
        return amountResult;
    }


    public void changeCurrency(String from, String to, int amount, String bank) {
        Course course;
        Optional<Course> record = coursesRepo.findByFromCurrencyAndToCurrencyAndBank(from, to, bank);
        if (record.isPresent()) {
            course = record.get();
            course.setHowCurrency(amount);
        } else {
            course = new Course();
            course.setToCurrency(to);
            course.setFromCurrency(from);
            course.setHowCurrency(amount);
            course.setBank(bank);
        }
        coursesRepo.save(course);
    }
}
