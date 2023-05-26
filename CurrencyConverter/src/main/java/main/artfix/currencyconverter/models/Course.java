package main.artfix.currencyconverter.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String fromCurrency, toCurrency, bank;
    private int howCurrency;
}
