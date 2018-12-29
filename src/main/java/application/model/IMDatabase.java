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

    public Accounting getAccountEntry(String id) {
        Accounting accountEntry = null;
        for (Accounting entry : accountEntries) {
            if (entry.getId().contains(id)) {
                accountEntry = entry;
            }
        }
        return accountEntry;
    }

    public String save(Accounting accountingEntry) {
        String uniqueId = UUID.randomUUID().toString();
        accountingEntry.setId(uniqueId);
        this.accountEntries.add(accountingEntry);
        return accountingEntry.getId();
    }

}
