package org.service.factory;

import org.springframework.stereotype.Component;

/**
 * Implementation of the Factory interface for a factory in Chennai.
 */
@Component("Chennai")
public class FactoryInChennai implements Factory {

    /**
     * Adds transportation cost for the factory in Chennai.
     *
     * @return The calculated transportation cost (in this case, always returns 50).
     */
    @Override
    public double addTransportationCost() {
        return 50;
    }
}
