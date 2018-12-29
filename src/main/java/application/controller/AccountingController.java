package application.controller;

import application.model.Accounting;
import application.model.IMDatabase;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestController
public class AccountingController implements ErrorController {

    private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private Validator validator = factory.getValidator();

    @GetMapping("/lancamentos-contabeis")
    private ArrayList<Accounting> getAll() {
        return IMDatabase.getInstance().getAccountEntries();
    }

    @GetMapping("/lancamentos-contabeis/{id}")
    private Accounting getAccountingEntry(@PathVariable String id) {
        return IMDatabase.getInstance().getAccountEntry(id);
    }

    @PostMapping("/lancamentos-contabeis")
    private ResponseEntity<JsonNode> newAccountEntry(@Valid @RequestBody Accounting newAccountEntry, Errors errors) {
        HttpStatus status;
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> responseJson = new HashMap<>();
        Set<ConstraintViolation<Accounting>> violations = validator.validate(newAccountEntry);
        if (violations.size() == 0) {
            responseJson.put("ID", IMDatabase.getInstance().save(newAccountEntry));
            status = HttpStatus.CREATED;
        } else {
            responseJson.put("Error", violations.iterator().next().getMessage());
            status = HttpStatus.BAD_REQUEST;
        }
        JsonNode jsonNode = objectMapper.valueToTree(responseJson);
        return new ResponseEntity<>(jsonNode, status);
    }

    @Override
    @GetMapping("/error")
    public String getErrorPath() {
        return "No Mapping Found";
    }
}
