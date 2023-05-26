package main.artfix.currencyconverter.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class RandomCodeService {
    public String getCode() {
        Random rand = new Random();
        int min = 1000;
        int max = 9999;
        int randomIntCode = rand.nextInt((max - min) + 1) + min;
        return String.valueOf(randomIntCode);
    }
}
