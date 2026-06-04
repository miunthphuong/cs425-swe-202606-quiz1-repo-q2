package com.cams;

import com.cams.model.Account;
import com.cams.repository.AccountRepository;
import com.cams.service.BankService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonDeserializer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Configure Gson to properly handle java.time.LocalDate without accessibility reflection issues
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, (JsonSerializer<LocalDate>) (src, typeOfSrc, context) ->
                        new com.google.gson.JsonPrimitive(src.format(DateTimeFormatter.ISO_LOCAL_DATE)))
                .registerTypeAdapter(LocalDate.class, (JsonDeserializer<LocalDate>) (json, typeOfT, context) ->
                        LocalDate.parse(json.getAsString(), DateTimeFormatter.ISO_LOCAL_DATE))
                .setPrettyPrinting()
                .create();

        // Initialize Architecture Layers
        AccountRepository repository = new AccountRepository();
        BankService service = new BankService(repository);

        System.out.println("Hello! Welcome to the Customer-Accounts Management App!");
        System.out.println("Starting the application tasks...\n");

        // -----------------------------------------------------------------------------------
        // TASK 1: JSON Array of All Accounts Data, sorted by Balance in descending order.
        // -----------------------------------------------------------------------------------
        System.out.println("Task 1: JSON Array of All Accounts Data, sorted by Balance in descending order.");
        System.out.println("-----------------------------------------------------------------------------------");
        List<Account> sortedAccounts = service.getAllAccountsSortedByBalanceDesc();
        System.out.println(gson.toJson(sortedAccounts));

        // Print Bank Liquidity Position
        System.out.printf("\n>> Bank Liquidity Position (Total Balance): $%,.2f\n\n", service.calculateLiquidityPosition());

        // -----------------------------------------------------------------------------------
        // TASK 2: JSON Array of Platinum tier Accounts.
        // -----------------------------------------------------------------------------------
        System.out.println("Task 2: JSON Array of Platinum tier Accounts.");
        System.out.println("-----------------------------------------------------------------------------------");
        List<Account> platinumAccounts = service.getPlatinumAccounts();
        System.out.println(gson.toJson(platinumAccounts));
    }
}
