package application.controller;


import application.model.Accounting;
import application.model.IMDatabase;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

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
    private ResponseEntity<JsonNode> newAccountEntry(@RequestBody Accounting newAccountEntry) {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> responseJson = new HashMap<>();
        responseJson.put("ID", IMDatabase.getInstance().save(newAccountEntry));
        JsonNode jsonNode = objectMapper.valueToTree(responseJson);
        return new ResponseEntity<>(jsonNode, HttpStatus.CREATED);
    }

}
