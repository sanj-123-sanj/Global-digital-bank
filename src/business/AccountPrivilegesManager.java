package business;

import java.util.HashMap;

import java.util.Map;

import entities.exceptions.InvalidPrivilegeException;
import entities.accounts.Privilege;

/**
 * Manages account privileges and their associated daily transfer limits.
 */
public class AccountPrivilegesManager {

    private static Map<Privilege, Double> privileges = new HashMap<>();

    // Static block to initialize privilege limits
    static {
        privileges.put(Privilege.PREMIUM, 100000.00);
        privileges.put(Privilege.GOLD, 50000.00);
        privileges.put(Privilege.SILVER, 25000.00);
    }

    /**
     * Retrieves the daily transfer limit for the specified account privilege.
     *
     * @param privilege the privilege level of the account
     * @return the daily transfer limit associated with the specified privilege
     * @throws InvalidPrivilegeException if the provided privilege is not recognized
     */
    public static double getPrivilegeLimit(Privilege privilege) throws InvalidPrivilegeException {
        double limit = 0.0;

        if (privileges.containsKey(privilege)) {
            limit = privileges.get(privilege);
        } else {
            throw new InvalidPrivilegeException("Invalid privilege");
        }

        return limit;
    }
}
	