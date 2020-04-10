package com.currencyexchange.backend.controllers;


import com.currencyexchange.backend.exceptions.CurrencyNotFoundException;
import com.currencyexchange.backend.models.Currency;
import com.currencyexchange.backend.repositories.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/homepage")
public class HomePageController {

    @Autowired
    private CurrencyRepository currencyRepository;

    //POST
    @PostMapping("/addCurrency")
    public @ResponseBody String addNewCurrency (@RequestParam String fullCurrencyName,
                                            @RequestParam String shortCurrencyName,
                                            @RequestParam float conversionFactor) {

        Currency entity = new Currency();
        entity.setShortCurrencyName(shortCurrencyName);
        entity.setConversionFactor(conversionFactor);
        entity.setFullCurrencyName(fullCurrencyName);
        currencyRepository.save(entity);
        return "Saved";
    }

    //GET for all entities
    @GetMapping()
    public @ResponseBody Iterable<Currency> getAllCurrencies() {
        return currencyRepository.findAll();
    }

    //GET for one entity
    @GetMapping("{id}")
    public @ResponseBody Currency getCurrency(@PathVariable Integer id) {
        return currencyRepository.findById(id).orElseThrow(() -> new CurrencyNotFoundException(id));
    }

    //PUT for an entity. If the entity is not found will create a new one
    @PutMapping("/edit/{id}")
    public @ResponseBody Currency replaceEmployee(@RequestBody Currency newCurrency, @PathVariable Integer id) {

        return currencyRepository.findById(id)
                .map(currency -> {
                    currency.setFullCurrencyName(newCurrency.getFullCurrencyName());
                    currency.setConversionFactor(newCurrency.getConversionFactor());
                    currency.setShortCurrencyName(newCurrency.getShortCurrencyName());
                    return currencyRepository.save(currency);
                })
                .orElseGet(() -> {
                    newCurrency.setId(id);
                    return currencyRepository.save(newCurrency);
                });
    }

    //DELETE
    @DeleteMapping("/employees/{id}")
    public @ResponseBody void deleteCurrency(@PathVariable Integer id) {
        currencyRepository.deleteById(id);
    }

}
