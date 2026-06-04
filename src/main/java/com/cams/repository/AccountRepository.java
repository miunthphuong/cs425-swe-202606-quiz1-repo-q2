package com.cams.repository;

import com.cams.model.Account;
import com.cams.model.Customer;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AccountRepository {
    private final List<Account> accounts = new ArrayList<>();

    public AccountRepository() {
        loadMockData();
    }

    private void loadMockData() {
        Customer c1 = new Customer("C001", "Mary", "Washington", LocalDate.of(1932, 5, 31));
        Customer c2 = new Customer("C002", "Ana", "Smith", LocalDate.of(1988, 10, 15));
        Customer c3 = new Customer("C003", "John", "Doe", LocalDate.of(1975, 12, 25));

        accounts.add(new Account("ACC-1001", 75000.50, "Platinum", c1));
        accounts.add(new Account("ACC-1002", 1200.00, "Silver", c2));
        accounts.add(new Account("ACC-1003", 150000.00, "Platinum", c3));
        accounts.add(new Account("ACC-1004", 5500.25, "Gold", c2));
    }

    public List<Account> getAllAccounts() {
        return new ArrayList<>(accounts);
    }
}