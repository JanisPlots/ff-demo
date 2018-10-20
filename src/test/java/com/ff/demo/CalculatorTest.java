package com.ff.demo;

import com.ff.demo.dal.model.Client;
import com.ff.demo.dal.model.LoanVersion;
import com.ff.demo.service.impl.ExtendLoanCalculationService;
import com.ff.demo.service.impl.NewLoanCalculationService;
import com.ff.demo.service.model.LoanContext;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

public class CalculatorTest extends BaseSpringTest {

    @Autowired
    private NewLoanCalculationService newLoanCalculationService;

    @Autowired
    private ExtendLoanCalculationService extendLoanCalculationService;

    @Test
    public void newLoanCalculationTest() {

        LoanContext loanContext = iContextLoader.loadNewContext(
                "0.0.0.0",
                BigDecimal.valueOf(1000),
                30
        );

        BigDecimal interestFactorExpected = loanContext.getInterestFactor();

        newLoanCalculationService.doCalculate(loanContext);

        BigDecimal interestFactorActual = loanContext
                .getLoan()
                .getLoanVersions()
                .get(0)
                .getInterestFactor();

        Assert.assertEquals(
                "Interest factor must be populated",
                interestFactorExpected,
                interestFactorActual
        );

    }

    @Test
    public void extendedLoanCalculationTest() {

        // Create loan to extend
        String clientIp = "0.0.0.1";
        iLoanProcessor.doApply(clientIp, BigDecimal.valueOf(100), 12);

        Client client = clientRepository.findByIp(clientIp);

        int extendedTerm = 14;
        LoanContext loanContext = iContextLoader.loadExtendContext(
                client.getId(),
                extendedTerm
        );
        extendLoanCalculationService.doCalculate(loanContext);

        BigDecimal interestFactorActual = loanContext
                .getLoan()
                .getLoanVersions()
                .stream()
                .filter(lv -> lv.getLoanVersionType().equals(LoanVersion.LoanVersionType.EXTENDED))
                .findFirst()
                .get()
                .getInterestFactor();

        int extensionInterestPerPeriodDays = loanContext.getExtensionInterestPerPeriodDays();
        BigDecimal extendedInterestFactorMultilayer = BigDecimal.valueOf(
                Math.ceil((double)extendedTerm/extensionInterestPerPeriodDays)
        );

        BigDecimal interestFactorExpected = loanContext
                .getExtensionInterestFactor()
                .multiply(extendedInterestFactorMultilayer);

        Assert.assertEquals(
                "Interest factor must be populated",
                interestFactorExpected,
                interestFactorActual
        );

    }
}
