package application.model;

import java.util.ArrayList;

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

    public Accounting save(Accounting accountingEntry) {
        this.accountEntries.add(accountingEntry);
        return accountingEntry;
    }

}
