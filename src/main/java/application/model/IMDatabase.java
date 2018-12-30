package application.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;

public class IMDatabase {

    private static IMDatabase instance;
    private ArrayList<Accounting> accountEntries = new ArrayList<>();

    private IMDatabase() {}

    public static IMDatabase getInstance() {
        if (instance == null) {
            instance = new IMDatabase();
        }
        return instance;
    }

    public ArrayList<Accounting> getAccountEntries() {
        return accountEntries;
    }

    public Accounting getRowById(String id) {
        Accounting accountEntry = null;
        for (Accounting entry : accountEntries) {
            if (entry.getId().equals(id)) {
                accountEntry = entry;
            }
        }
        return accountEntry;
    }

    public ArrayList<Accounting> getRowsByAccount(Integer contaContabil) {
        ArrayList<Accounting> entries = new ArrayList<>();
        for (Accounting entry: accountEntries) {
            if (entry.getContaContabil().equals(contaContabil)) {
                entries.add(entry);
            }
        }
        return entries;
    }

    public Statistics getStats() {
        long soma;
        int qtde = 0;
        Statistics stats = new Statistics();
        ArrayList<Long> numbers = new ArrayList<>();
        for (Accounting entry: accountEntries) {
            numbers.add(entry.getValor().longValue());
            qtde++;
        }
        if (numbers.size() > 0) {
            stats.setMax(BigDecimal.valueOf(Collections.max(numbers)));
            stats.setMin(BigDecimal.valueOf(Collections.min(numbers)));
            soma = numbers.stream().reduce(0L, (e1, e2) -> e1 + e2);
            stats.setSoma(BigDecimal.valueOf(soma));
            stats.setMedia(BigDecimal.valueOf(soma / qtde));
            stats.setQtde(qtde);
            return stats;
        } else {
            return null;
        }
    }

    public String save(Accounting accountingEntry) {
        String uniqueId = UUID.randomUUID().toString();
        accountingEntry.setId(uniqueId);
        this.accountEntries.add(accountingEntry);
        return accountingEntry.getId();
    }

}
