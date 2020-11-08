/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package local.shemi.simplebanking.webapi.customer;

import org.springframework.data.annotation.Id;

/**
 *
 * @author shemistone
 */
public class AtmCard {

    @Id
    private String id = "";
    private String xprDate = "";
    private String accountId = "";
    private String pin = "";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getXprDate() {
        return xprDate;
    }

    public void setXprDate(String xprDate) {
        this.xprDate = xprDate;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

}
