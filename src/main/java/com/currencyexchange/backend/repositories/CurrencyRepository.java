package com.currencyexchange.backend.repositories;

import com.currencyexchange.backend.models.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<Currency,Integer> {
}
