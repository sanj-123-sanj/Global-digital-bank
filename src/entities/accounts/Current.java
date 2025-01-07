package entities.accounts;

import entities.accounts.Account;

/**
* Represents a current account, typically used by businesses.
* Inherits from the base Account class and adds attributes specific to
* business accounts, such as company details.
*/
public class Current extends Account {
    
    // Name of the company associated with the current account
    private String companyName;
    
    // web-site URL of the company
    private String websiteURL;
    
    // Unique registration number of the company
    private String registrationNumber;
 
    /**
     * Gets the company name associated with the current account.
     *
     * @return the name of the company
     */
    public String getCompanyName() {
        return companyName;
    }
 
    /**
     * Sets the company name associated with the current account.
     *
     * @param companyName the name of the company
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
 
    /**
     * Gets the website URL of the company.
     *
     * @return the website URL of the company
     */
    public String getWebsiteURL() {
        return websiteURL;
    }
 
    /**
     * Sets the website URL of the company.
     *
     * @param websiteURL the website URL of the company
     */
    public void setWebsiteURL(String websiteURL) {
        this.websiteURL = websiteURL;
    }
 
    /**
     * Gets the registration number of the company.
     *
     * @return the registration number of the company
     */
    public String getRegistrationNumber() {
        return registrationNumber;
    }
 
    /**
     * Sets the registration number of the company.
     *
     * @param registrationNumber the registration number of the company
     */
    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }
}