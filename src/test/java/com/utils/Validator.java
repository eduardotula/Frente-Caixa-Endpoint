package com.utils;
import javax.enterprise.context.ApplicationScoped;

import static org.assertj.core.api.Assertions.*;

@ApplicationScoped
public class Validator{


    public <T> void validate(T actual, T expected, String... ignoreFiels){
        assertThat(actual).usingRecursiveComparison().withStrictTypeChecking()
                .ignoringFields(ignoreFiels)
                .isEqualTo(expected);
    }
}
