package org.service.factory;

import org.springframework.stereotype.Component;

/**
 * Implementation of the Factory interface for a factory in Bangalore.
 */
@Component("Bangalore")
public class FactoryInBangalore implements Factory {

    /**
     * Adds transportation cost for the factory in Bangalore.
     *
     * @return The calculated transportation cost (in this case, always returns 0).
     */
    @Override
    public double addTransportationCost() {
        return 0;
    }
}
