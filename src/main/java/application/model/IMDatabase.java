package application.model;

import java.util.ArrayList;
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

    public String save(Accounting accountingEntry) {
        String uniqueId = UUID.randomUUID().toString();
        accountingEntry.setId(uniqueId);
        this.accountEntries.add(accountingEntry);
        return accountingEntry.getId();
    }

}
