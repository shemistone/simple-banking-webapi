package local.shemi.simplebanking.webapi.customer;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

/**
 * Created by shemistone on 13/07/17.
 */
public class Account {

    @Id
    private String id = "";
    private String name = "";
    private String customerId = "";
    @Transient
    private List<AtmCard> atmCards = new ArrayList<>();

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

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public List<AtmCard> getAtmCards() {
        return atmCards;
    }

    public void setAtmCards(List<AtmCard> atmCards) {
        this.atmCards = atmCards;
    }

}
