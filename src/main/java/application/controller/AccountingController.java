package application.controller;


import application.model.Accounting;
import application.model.IMDatabase;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestController
public class AccountingController implements ErrorController {

    private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private Validator validator = factory.getValidator();

    @GetMapping("/get-accounting")
    private Accounting GetAccountingFirst() {
        System.out.println("test");
        return new Accounting(1, 1, new BigDecimal(1000));
    }

    @Override
    @GetMapping("/error")
    public String getErrorPath() {
        return "No Mapping Found";
    }

    @PostMapping("/lancamentos-contabeis")
    private ResponseEntity<JsonNode> newAccountEntry(@Valid @RequestBody Accounting newAccountEntry, Errors errors) {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> responseJson = new HashMap<>();
        Set<ConstraintViolation<Accounting>> violations = validator.validate(newAccountEntry);
        if (violations.size() == 0) {
            responseJson.put("ID", IMDatabase.getInstance().save(newAccountEntry));
            JsonNode jsonNode = objectMapper.valueToTree(responseJson);
            return new ResponseEntity<>(jsonNode, HttpStatus.CREATED);
        } else {
            responseJson.put("Error", violations.iterator().next().getMessage());
            JsonNode jsonNode = objectMapper.valueToTree(responseJson);
            return new ResponseEntity<>(jsonNode, HttpStatus.BAD_REQUEST);
        }
    }

}
