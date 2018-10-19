package com.ff.demo.rest.controller;

import com.ff.demo.dal.model.Client;
import com.ff.demo.dal.repository.ClientRepository;
import com.ff.demo.dal.repository.LoanRepository;
import com.ff.demo.rest.model.RestLoan;
import com.ff.demo.rest.model.RestLoanRequest;
import com.ff.demo.rest.model.RestLoanResponse;
import com.ff.demo.service.ILoanProcessor;
import com.ff.demo.service.model.LoanResult;
import com.ff.demo.util.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@Api(description = "Basic loan API")
public class LoanController {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ILoanProcessor iLoanProcessor;

    @Autowired
    private ObjectMapper mapper;



    @PostMapping("/loans/apply")
    @ApiOperation(value = "Apply to new loan")
    public RestLoanResponse apply(
            @Valid @RequestBody RestLoanRequest loanRequest,
            HttpServletRequest request
    ) {

        LoanResult loanResult = iLoanProcessor.doApply(
                request.getRemoteAddr(),
                loanRequest.getAmount(),
                loanRequest.getTermDays()
        );
        return new RestLoanResponse(loanResult.isAccepted());
    }

    @PostMapping("/loans/{loanId}/extend")
    @ApiOperation(value = "Extend term of existing loan")
    public RestLoanResponse extend(@PathVariable Long loanId) {

        LoanResult loanResult = iLoanProcessor.doExtend(loanId);

        return new RestLoanResponse(loanResult.isAccepted());
    }

    @GetMapping("/loans")
    @ApiOperation(value = "Retrieve loans by client ip")
    public List<RestLoan> getClientIpLoans(
            HttpServletRequest request
    ) {
        Client client = clientRepository.findByIp(request.getRemoteAddr());
        return mapper.map(
                loanRepository.findByClientId(client.getId()),
                RestLoan.class
        );
    }
}
