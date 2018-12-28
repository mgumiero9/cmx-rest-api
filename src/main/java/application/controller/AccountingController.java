package application.controller;


import application.model.Accounting;
import application.model.IMDatabase;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class AccountingController implements ErrorController {

    private final static String GET_ACCOUNTING_PATH = "/get-accounting";
    private final static String ERRORPATH = "/error";

    @GetMapping(GET_ACCOUNTING_PATH)
    private Accounting GetAccountingFirst() {
        System.out.println("test");
        return new Accounting(1, 1, new BigDecimal(1000));
    }

    @Override
    @GetMapping(ERRORPATH)
    public String getErrorPath() {
        return "No Mapping Found";
    }

    @PostMapping("/lancamentos-contabeis")
    private Accounting newAccountEntry(@RequestBody Accounting newAccountEntry) {
        return IMDatabase.getInstance().save(newAccountEntry);
    }

}
