package com.imolczek.school.banking.rest.service;

import java.math.BigDecimal;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.imolczek.school.banking.loan.calculator.service.exceptions.LoanCalculationServiceException;
import com.imolczek.school.banking.loan.model.LoanCalculationResult;

/**
 * @author Fabian Bouché
 * A loan simulation REST service
 */
public interface LoanSimulationService {

    /**
     * Simulate the loan result for a known amount, installment and rate
     * @param amount the amount of the loan
     * @param installment the desired monthly installment
     * @param rate the annual rate
     * @return a loan simulation result
     * @throws LoanCalculationServiceException 
     */
    @GET
    @Path("/calculate/amount/{amount}/installment/{installment}/rate/{rate}/")
    @Produces("application/json")
    public LoanCalculationResult simulate(@PathParam("amount") BigDecimal amount, @PathParam("installment") BigDecimal installment, @PathParam("rate") BigDecimal rate) throws LoanCalculationServiceException;
    
}
