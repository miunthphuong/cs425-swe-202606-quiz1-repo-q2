package com.cams.service;

import com.cams.model.Account;
import com.cams.repository.AccountRepository;
import java.util.List;
import java.util.stream.Collectors;

public class BankService {
    private final AccountRepository accountRepository;

    public BankService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    // TASK 1: Get all accounts sorted by balance in descending order
    public List<Account> getAllAccountsSortedByBalanceDesc() {
        return accountRepository.getAllAccounts().stream()
                .sorted((a1, a2) -> Double.compare(a2.getBalance(), a1.getBalance()))
                .collect(Collectors.toList());
    }

    // TASK 1 Add-on: Calculate Liquidity Position (Total Balance)
    public double calculateLiquidityPosition() {
        return accountRepository.getAllAccounts().stream()
                .mapToDouble(Account::getBalance)
                .sum();
    }

    // TASK 2: Get all Platinum tier accounts
    public List<Account> getPlatinumAccounts() {
        return accountRepository.getAllAccounts().stream()
                .filter(account -> "Platinum".equalsIgnoreCase(account.getAccountType()))
                .collect(Collectors.toList());
    }
}