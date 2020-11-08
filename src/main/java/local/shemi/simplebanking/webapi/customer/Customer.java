package local.shemi.simplebanking.webapi.customer;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

/**
 * Created by shemistone on 20/02/17.
 */
public class Customer implements Serializable {

    @Id
    private String id = "";
    private String mobileNo = "";
    private String firstName = "";
    private String middleName = "";
    private String lastName = "";
    private String pin = "";
    private String imsi = "";
    private boolean firstLogin;
    private boolean active;
    private String lang = "";
    private int failedLogins;
    private LocalDateTime timeCreated = LocalDateTime.now();
    private LocalDateTime timeUpdated = LocalDateTime.now();
    @Transient
    private List<Account> accounts = new ArrayList<>();
    @Transient
    private List<Account> linkedAccounts = new ArrayList<>();
    @Transient
    private List<Account> unlinkedAccounts = new ArrayList<>();

    public boolean hasMultipleAccounts() {
        return (this.accounts.size() > 1);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(LocalDateTime timeCreated) {
        this.timeCreated = timeCreated;
    }

    public LocalDateTime getTimeUpdated() {
        return timeUpdated;
    }

    public void setTimeUpdated(LocalDateTime timeUpdated) {
        this.timeUpdated = timeUpdated;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public void setFirstLogin(boolean firstLogin) {
        this.firstLogin = firstLogin;
    }

    public boolean isFirstLogin() {
        return firstLogin;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getLang() {
        if (lang != null) {
            return lang.toUpperCase();
        }
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public int getFailedLogins() {
        return failedLogins;
    }

    public void setFailedLogins(int failedLogins) {
        this.failedLogins = failedLogins;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public List<Account> getLinkedAccounts() {
        return linkedAccounts;
    }

    public void setLinkedAccounts(List<Account> linkedAccounts) {
        this.linkedAccounts = linkedAccounts;
    }

    public List<Account> getUnlinkedAccounts() {
        return unlinkedAccounts;
    }

    public void setUnlinkedAccounts(List<Account> unlinkedAccounts) {
        this.unlinkedAccounts = unlinkedAccounts;
    }

    public List<String> accountNos() {
        List<String> accountNos = new ArrayList<>();
        this.accounts.forEach(account -> {
            accountNos.add(account.getId());
        });
        return accountNos;
    }

}
