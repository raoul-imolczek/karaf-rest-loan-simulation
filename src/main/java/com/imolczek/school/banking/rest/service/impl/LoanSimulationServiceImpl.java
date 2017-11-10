package com.imolczek.school.banking.rest.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.cxf.dosgi.common.api.IntentsProvider;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.imolczek.school.banking.loan.calculator.service.LoanCalculationService;
import com.imolczek.school.banking.loan.calculator.service.exceptions.LoanCalculationServiceException;
import com.imolczek.school.banking.loan.model.LoanCalculationResult;
import com.imolczek.school.banking.rest.service.LoanSimulationService;

import static java.util.Arrays.asList;

/**
 * @author Fabian Bouché
 * Implementation of the loan simulation service
 */
@Component (
		immediate = true,
		name = "LoanSimulationService",
		property = {
				"service.exported.interfaces=com.imolczek.school.banking.rest.service.LoanSimulationService", 
				"service.exported.configs=org.apache.cxf.rs", 
				"org.apache.cxf.rs.address=/simulation",
				"cxf.bus.prop.skip.default.json.provider.registration=true"
		}
		)
public class LoanSimulationServiceImpl implements LoanSimulationService, IntentsProvider {

	@Override
	public LoanCalculationResult simulate(BigDecimal amount, BigDecimal installment, BigDecimal rate) throws LoanCalculationServiceException {
		return loanCalculationService.calculate(amount, installment, rate);
	}
	
	/**
	 * An instance of the loan calculation service (Declarative service)
	 */
	@Reference(unbind="-")
	private LoanCalculationService loanCalculationService;

	@Override
	public List<?> getIntents() {
		return asList(new JacksonJaxbJsonProvider());
	}
	
}
