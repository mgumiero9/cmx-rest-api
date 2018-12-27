package application.controller;


import application.model.Accounting;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class AccountingController implements ErrorController {

    private final static String GET_ACCOUNTING_PATH = "/get-accounting";
    private final static String ERRORPATH = "/error";

    @RequestMapping(GET_ACCOUNTING_PATH)
    public Accounting accountingGetFirst() {
        System.out.println("test");
        return new Accounting(1, 1, new BigDecimal(1000));
    }

    @Override
    @RequestMapping(ERRORPATH)
    public String getErrorPath() {
        return "No Mapping Found";
    }

}
