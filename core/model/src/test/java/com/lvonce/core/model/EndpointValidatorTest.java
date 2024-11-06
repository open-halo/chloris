package com.lvonce.core.model;

import com.lvonce.core.model.base.ValidatorTest;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class EndpointValidatorTest extends ValidatorTest<Endpoint> {
    private static Stream<Arguments> invalidEntities() {
        return Stream.of(Arguments.of(givenEndpointWithNullID()),
            Arguments.of(givenEndpointWithEmptyName()));
    }

    private static Stream<Arguments> validEntities() {
        return Stream.of(Arguments.of(givenValidEndpoint1()));
    }

    private static Endpoint givenValidEndpoint1() {
        return new Endpoint(123L, "GET", "www.example.com", "13912341234");
    }

    public static Endpoint givenEndpointWithNullID() {
        return new Endpoint(null, "Julio", "hello@s.com", "123");
    }

    public static Endpoint givenEndpointWithEmptyName() {
        return new Endpoint(123L, "", "hello@s", "123");
    }

}
