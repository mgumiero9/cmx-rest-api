package application.controller;

import application.exception.InputException;
import application.model.Accounting;
import application.model.IMDatabase;
import application.model.Statistics;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class AccountingController implements ErrorController {

    private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private Validator validator = factory.getValidator();
    private ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("/lancamentos-contabeis/all")
    private ArrayList<Accounting> getAll() {
        return IMDatabase.getInstance().getAccountEntries();
    }

    @GetMapping("/lancamentos-contabeis/{id}")
    private ResponseEntity<JsonNode> getById(@PathVariable String id) {
        HttpStatus status;
        JsonNode jsonNode;
        Map<String, String> map = new HashMap<>();
        Accounting responseJson = IMDatabase.getInstance().getRowById(id);
        if (responseJson != null) {
            jsonNode = objectMapper.valueToTree(responseJson);
            status = HttpStatus.CREATED;
        } else {
            map.put("Info","No Content found");
            jsonNode = objectMapper.valueToTree(map);
            status = HttpStatus.NO_CONTENT;
        }
        return new ResponseEntity<>(jsonNode, status);
    }

    @GetMapping("/lancamentos-contabeis")
    private @ResponseBody ArrayList<Accounting> getByAccount(@RequestParam Integer contaContabil) {
        return IMDatabase.getInstance().getRowsByAccount(validateAccount(contaContabil));
    }

    @GetMapping("/lancamentos-contabeis/_stats")
    private @ResponseBody Statistics getStatistics(Integer contaContabil) {
        return IMDatabase.getInstance().getStats(validateAccount(contaContabil));
    }

    @PostMapping("/lancamentos-contabeis")
    private ResponseEntity<JsonNode> newAccountEntry(@Valid @RequestBody Accounting newAccountEntry, Errors errors) {
        HttpStatus status;
        validateDate(newAccountEntry);
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

    private void validateDate(@RequestBody @Valid Accounting newAccountEntry) throws InputException {
        final String dataStr = newAccountEntry.getData().toString();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        try {
            Date date = format.parse(dataStr);
            if (!format.format(date).equals(dataStr.trim())) {
                throw new InputException("Date is not valid.");
            }
        } catch (ParseException e) {
            e.printStackTrace();
            throw new InputException("Date is not valid.");
        }
    }

    private Integer validateAccount(Integer contaContabil) throws InputException {
        if (contaContabil == null) return null;
        final String regex = "^\\d{1,8}$";
        final String string = String.valueOf(contaContabil);

        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(string);

        if (matcher.find()) {
            return contaContabil;
        } else {
            throw new InputException("contaContabil accepts maximum of 8 digit values");
        }
    }

    @Override
    @GetMapping("/error")
    public String getErrorPath() {
        return "Error: Bad Request.";
    }

}
