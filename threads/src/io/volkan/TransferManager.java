package io.volkan;

public class TransferManager implements Runnable {
    private CustomerAccount fromAccount;
    private CustomerAccount toAccount;
    private int transferCount;

    public TransferManager(CustomerAccount from, CustomerAccount to, int transfers) {
        fromAccount = from;
        toAccount = to;
        transferCount = transfers;
    }

    /*
        Any access to a shared resource is better-off synchronized.
        That resource can be a primitive like a number, os a complex object like a JProgressBar

        In a typical swing application the AWT Event Thread is the owner of, and responsible for
        the updates to a JProgressBar component after is becomes visible on the screen.

        So if you try to `.setValue()` of a `JProgressBar` from a separate thread T1, then
        essentially you are accessing a shared resource that T1 and AWT Event thread have
        concurrent access.

        Swing provides `SwingUtilities.invokeAndWait()` and `SwingUtilities.invokeLater()` methods
        to facilitate this communication; mainly yielding the executing of access to the shared
        resources (the `.setValue()` example in the above paragraph) back to the AWT thread.

        So with `invokeLater()` or `invokeAndWait()`, AWT will thread will update the shared
        resource that T1 tells it to update, whenever it thinks is the best time to update it.
     */

    public void run() {
        double balance;
        double transferAmount;

        for (int i = 0; i < transferCount; i++) {
            balance = fromAccount.getBalance();
            transferAmount = (int)(balance * Math.random());
            balance -= transferAmount;
            fromAccount.setBalance(balance);
            balance = toAccount.getBalance();
            balance += transferAmount;
            toAccount.setBalance(balance);
        }
    }
}
