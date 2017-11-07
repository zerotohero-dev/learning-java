package io.volkan;

import java.text.NumberFormat;
import java.util.Locale;

public class AccountManager {
    private CustomerAccount savings;
    private CustomerAccount checking;

    public final static int SAVINGS_ACCOUNT = 1;
    public final static int CHECKING_ACOUNT = 2;

    public static void main(String[] args) {
        int transfers = 1000000;

        try {
            transfers = Integer.parseInt(args[0]);
        } catch (Exception e) {}

        AccountManager am = new AccountManager(transfers);
    }

    public AccountManager(int transfers) {
        savings = new CustomerAccount(SAVINGS_ACCOUNT, 1000);
        checking = new CustomerAccount(CHECKING_ACOUNT, 1000);

        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);

        System.out.printf(
            "Total balance before transfers: %s.%n",
            formatter.format(savings.getBalance() + checking.getBalance())
        );

        TransferManager tm1 = new TransferManager(checking, savings, transfers);
        TransferManager tm2 = new TransferManager(savings, checking, transfers);

        Thread t1 = new Thread(tm1);
        Thread t2 = new Thread(tm2);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch(Exception e) {}

        System.out.printf(
            "Total balance after transfers: %s.%n",
            formatter.format(savings.getBalance() + checking.getBalance())
        );
    }
}
