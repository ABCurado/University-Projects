package eapli.ecafeteria.domain.meals;

import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 * TODO javadoc
 *
 * @author hichampt
 */
@Embeddable
public class QuantitySold implements Serializable {

    private int qtdReserved;

    public QuantitySold() {
        //FIXME validate
        this.qtdReserved = 0;
    }

    /**
     *
     * @param number
     * @return total of meals reserved.
     */
    public boolean addReserve() {
        this.qtdReserved++;
        return true;
    }

    /**
     * consult qtdReserved of reservations made.
     *
     * @return
     */
    public int quantityReserved() {
        return this.qtdReserved;
    }

}
