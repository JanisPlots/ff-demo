package com.ff.demo;


import com.ff.demo.dal.model.Loan;
import com.ff.demo.service.IContextLoader;
import com.ff.demo.service.impl.uwrules.UWMaxAmountValidator;
import com.ff.demo.service.impl.uwrules.UWMaxLoansValidator;
import com.ff.demo.service.impl.uwrules.UWTimeValidator;
import com.ff.demo.service.model.LoanContext;
import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class UnderwritingRuleTest extends BaseSpringTest {

    @Autowired
    private UWMaxLoansValidator uwMaxLoansValidator;

    @Autowired
    private UWMaxAmountValidator uwMaxAmountValidator;

    @Autowired
    private UWTimeValidator uwTimeValidator;

    @Autowired
    private IContextLoader iContextLoader;

    @Test
    public void maxAmountValidationTest() {
        {

            LoanContext loanContext = iContextLoader.loadNewContext(
                    "0.0.0.0",
                    BigDecimal.valueOf(1001),
                    30
            );
            uwMaxAmountValidator.doValidate(loanContext);

            Assert.assertTrue(loanContext.isRejected());
        }

        {
            LoanContext loanContext = iContextLoader.loadNewContext(
                    "0.0.0.0",
                    BigDecimal.valueOf(1000),
                    30
            );
            uwMaxAmountValidator.doValidate(loanContext);

            Assert.assertFalse(loanContext.isRejected());
        }
    }

    @Test
    public void maxLoansValidationTest() {

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());

        Loan loan1 = new Loan();
        cal.add(Calendar.HOUR, -1);
        Date oneHourBack = cal.getTime();
        loan1.setCreatedAt(oneHourBack);

        Loan loan2 = new Loan();
        cal.add(Calendar.HOUR, -1);
        Date twoHoursBack = cal.getTime();
        loan2.setCreatedAt(twoHoursBack);

        Loan loan3 = new Loan();
        cal.add(Calendar.HOUR, -1);
        Date threeHoursBack = cal.getTime();
        loan3.setCreatedAt(threeHoursBack);

        List<Loan> loans = new ArrayList<>();
        loans.add(loan1);
        loans.add(loan2);
        loans.add(loan3);

        {
            LoanContext loanContext = iContextLoader.loadNewContext(
                    "0.0.0.0",
                    BigDecimal.valueOf(1000),
                    30
            );
            loanContext.setAllLoans(loans);

            uwMaxLoansValidator.doValidate(loanContext);

            Assert.assertTrue(loanContext.isRejected());
        }


        {
            // Time travel
            DateTime date = new DateTime(cal.get(Calendar.YEAR) + 1, 1, 1, 0, 0);
            DateTimeUtils.setCurrentMillisFixed(date.getMillis());

            LoanContext loanContext = iContextLoader.loadNewContext(
                    "0.0.0.0",
                    BigDecimal.valueOf(1000),
                    30
            );

            loanContext.setAllLoans(loans);

            uwMaxLoansValidator.doValidate(loanContext);

            Assert.assertFalse(loanContext.isRejected());
        }

        DateTimeUtils.setCurrentMillisSystem();
    }

    @Test
    public void midnightValidatorTest() {

        {
            // Time travel after midnight
            DateTimeUtils.setCurrentMillisFixed(new DateTime(2010, 1, 1, 0, 5).getMillis());

            LoanContext loanContext = iContextLoader.loadNewContext(
                    "0.0.0.0",
                    BigDecimal.valueOf(1000),
                    30
            );
            uwTimeValidator.doValidate(loanContext);

            Assert.assertTrue(loanContext.isRejected());
        }

        {
            // Time travel before midnight
            DateTimeUtils.setCurrentMillisFixed(new DateTime(2010, 1, 1, 10, 5).getMillis());

            LoanContext loanContext = iContextLoader.loadNewContext(
                    "0.0.0.0",
                    BigDecimal.valueOf(1000),
                    30
            );
            uwTimeValidator.doValidate(loanContext);

            Assert.assertFalse(loanContext.isRejected());
        }

        DateTimeUtils.setCurrentMillisSystem();

    }
}
