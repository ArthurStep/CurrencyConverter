package main.artfix.currencyconverter.repos;

import main.artfix.currencyconverter.models.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CoursesRepo extends CrudRepository<Course, Long> {
    Course findByFromCurrencyAndBank(String from, String bank);

    Optional<Course> findByFromCurrencyAndToCurrencyAndBank(String from, String to, String bank);

}
