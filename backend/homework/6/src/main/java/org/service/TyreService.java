package org.service;

import org.beans.Tyre;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class TyreService {
    @Bean(name = "Bridgestone")
    @Primary
    Tyre bridgestoneTyre(){
        return new Tyre("Bridgestone", 8000);
    }

    @Bean(name = "MRF")
    Tyre mrfTyre(){
        return new Tyre("MRF", 9000);
    }
}
