package controller;

import model.Accounting;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class AccountingController {

    @RequestMapping("/get-accounting")
    public Accounting accountingGetFirst() {
        return new Accounting(1, 1, new BigDecimal(1000));
    }
}
