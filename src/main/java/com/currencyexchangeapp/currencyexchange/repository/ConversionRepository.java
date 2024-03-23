package com.currencyexchangeapp.currencyexchange.repository;

import com.currencyexchangeapp.currencyexchange.model.Conversion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConversionRepository extends JpaRepository<Conversion, Integer> {

    List<Conversion> findAll();

}
