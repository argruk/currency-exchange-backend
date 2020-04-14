package com.currencyexchange.backend.controllers;


import com.currencyexchange.backend.dto.CurrencyAddReceived;
import com.currencyexchange.backend.dto.IdReceived;
import com.currencyexchange.backend.exceptions.CurrencyNotFoundException;
import com.currencyexchange.backend.models.Currency;
import com.currencyexchange.backend.repositories.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000",allowedHeaders = "*")
@RestController
@RequestMapping("/api/homepage")
public class HomePageController {

    @Autowired
    private CurrencyRepository currencyRepository;

    //POST
    @PostMapping("/addCurrency")
    public @ResponseBody String addNewCurrency (@RequestBody CurrencyAddReceived jsonString) {

        Currency entity = new Currency();
        entity.setShortCurrencyName(jsonString.getShortCurrencyName());
        entity.setConversionFactor(jsonString.getConversionFactor());
        entity.setFullCurrencyName(jsonString.getFullCurrencyName());
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
    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    public @ResponseBody Currency replaceEmployee(@RequestBody Currency newCurrency) {

        return currencyRepository.findById(newCurrency.getId())
                .map(currency -> {
                    currency.setFullCurrencyName(newCurrency.getFullCurrencyName());
                    currency.setConversionFactor(newCurrency.getConversionFactor());
                    currency.setShortCurrencyName(newCurrency.getShortCurrencyName());
                    return currencyRepository.save(currency);
                })
                .orElseGet(() -> {
                    return currencyRepository.save(newCurrency);
                });
    }

    //DELETE
    @RequestMapping(value = "/currencies", method = RequestMethod.DELETE)
        public @ResponseBody String deleteCurrency(@RequestBody IdReceived jsonString) {

        currencyRepository.deleteById(Integer.parseInt(jsonString.getId()));
        return "deleted";
    }

}
