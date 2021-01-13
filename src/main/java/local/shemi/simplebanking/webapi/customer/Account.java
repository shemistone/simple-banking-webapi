package local.shemi.simplebanking.webapi.customer;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;

/**
 * Created by shemistone on 13/07/17.
 */
public class Account {

    @Id
    @Column("account_no")
    private String id = "";
    @Column("account_name")
    private String name = "";
    private String customerCode = "";
    @Transient
    private Customer customer = new Customer();
    @Transient
    private List<AtmCard> atmCards = new ArrayList<>();

    public Account() {
    }

    public Account(String id, String name, String customerCode) {
        this.id = id;
        this.name = name;
        this.customerCode = customerCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<AtmCard> getAtmCards() {
        return atmCards;
    }

    public void setAtmCards(List<AtmCard> atmCards) {
        this.atmCards = atmCards;
    }

}
