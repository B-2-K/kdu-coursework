package org.service;

import org.springframework.stereotype.Component;
import org.utility.Tyre;
import org.springframework.context.annotation.Primary;

/**
 * Service class responsible for providing Tyre instances.
 */
@Component
public class TyreService {

    /**
     * Provides a Bridgestone Tyre instance.
     *
     * @return Bridgestone Tyre instance.
     */
    @Primary
    Tyre bridgestoneTyre() {
        return new Tyre("Bridgestone", 8000);
    }

    /**
     * Provides an MRF Tyre instance.
     *
     * @return MRF Tyre instance.
     */
    Tyre mrfTyre() {
        return new Tyre("MRF", 9000);
    }
}
