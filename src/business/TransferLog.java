package business;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import entities.accounts.*;

/**
 * Manages the logging and retrieval of transfer transactions for accounts.
 * Tracks transfers on a daily basis and allows retrieval of specific account transfer history.
 */
public class TransferLog {

    // Stores transfers for each day, mapped by the date
    HashMap<LocalDate, List<Transfer>> transferLog = new HashMap<>();

    /**
     * Retrieves a list of transfers associated with a given account for the current day.
     *
     * @param account the account for which to retrieve transfers
     * @return a list of {@code Transfer} objects for the specified account, or an empty list if no transfers are found
     */
    public List<Transfer> getTransfers(Account account) {
        List<Transfer> transfers = new ArrayList<>();

        // Get transfers for the current day
        transfers = transferLog.get(LocalDate.now());

        // Check for the specific account within today's transfers
        if (transfers != null) {
            List<Transfer> accountTransfers = new ArrayList<>();
            for (Transfer transfer : transfers) {
                if (account.getName().equalsIgnoreCase(transfer.getFromAccount().getName())) {
                    accountTransfers.add(transfer);
                }
            }
            return accountTransfers;
        }
        return transfers != null ? transfers : new ArrayList<>();
    }

    /**
     * Logs a new transfer for the current day. If transfers already exist for the day,
     * the transfer is appended to the list; otherwise, a new list is created.
     *
     * @param transfer the {@code Transfer} object to log
     */
    public void logTransfer(Transfer transfer) {
        // Log the transfer for today, creating or updating the transfer list
        transferLog.computeIfAbsent(LocalDate.now(), k -> new ArrayList<>()).add(transfer);
    }
}
