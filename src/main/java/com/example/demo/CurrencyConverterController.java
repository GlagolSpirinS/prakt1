package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@Controller
public class CurrencyConverterController {

    @GetMapping("/currency-converter")
    public String currencyConverter() {
        return "currency-converter";
    }

    @PostMapping("/convert")
    public String convert(
            @RequestParam("fromCurrency") String fromCurrency,
            @RequestParam("toCurrency") String toCurrency,
            @RequestParam("amount") double amount,
            Model model
    ) {
        double conversionRate = getConversionRate(fromCurrency, toCurrency);
        double convertedAmount = amount * conversionRate;

        model.addAttribute("fromCurrency", fromCurrency);
        model.addAttribute("toCurrency", toCurrency);
        model.addAttribute("amount", amount);
        model.addAttribute("convertedAmount", convertedAmount);
        return "conversion-result";
    }

    private double getConversionRate(String fromCurrency, String toCurrency) {
        if (fromCurrency.equals(toCurrency)) {
            return 1.0;
        }

        switch (fromCurrency) {
            case "USD":
                if (toCurrency.equals("EUR")) return 0.93;
                if (toCurrency.equals("RUB")) return 98.00;
                break;
            case "EUR":
                if (toCurrency.equals("USD")) return 1.08;
                if (toCurrency.equals("RUB")) return 105.00;
                break;
            case "RUB":
                if (toCurrency.equals("USD")) return 0.010;
                if (toCurrency.equals("EUR")) return 0.0095;
                break;
        }
        return 1.0;
    }
}
