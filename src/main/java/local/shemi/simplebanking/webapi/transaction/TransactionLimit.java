/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package local.shemi.simplebanking.webapi.transaction;

import java.io.Serializable;
import java.math.BigDecimal;
import org.springframework.data.annotation.Id;

/**
 *
 * @author shemistone
 */
public class TransactionLimit implements Serializable {

    @Id
    private String id;
    private BigDecimal min;
    private BigDecimal max;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getMin() {
        return min;
    }

    public void setMin(BigDecimal min) {
        this.min = min;
    }

    public BigDecimal getMax() {
        return max;
    }

    public void setMax(BigDecimal max) {
        this.max = max;
    }

}
