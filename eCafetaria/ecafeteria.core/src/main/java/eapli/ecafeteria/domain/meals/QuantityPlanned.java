package eapli.ecafeteria.domain.meals;

import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 * TODO javadoc
 *
 * @author hichampt
 */
@Embeddable
public class QuantityPlanned implements Serializable {

    private int qtdPlanned;

    protected QuantityPlanned() {
    }

    /**
     * Constructor
     *
     * @param qtdPlanned
     */
    public QuantityPlanned(int qtdPlanned) {
        //FIXME validate
        this.qtdPlanned = qtdPlanned;
    }

    /**
     *
     * @param number
     * @return total of meals reserved.
     */
    public boolean addPlanned() {
        this.qtdPlanned++;
        return true;
    }

    /**
     * consult qtdPlanned of meals planed
     *
     * @return return qtdPlanned of meals.
     */
    public int quantityPlanned() {
        return this.qtdPlanned;
    }

    /**
     * Constructor
     *
     * @param quantity
     */
    public void changePlaned(int quantity) {
        this.qtdPlanned = quantity;
    }
}
