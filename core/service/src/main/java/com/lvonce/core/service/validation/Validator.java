package com.lvonce.core.service.validation;

public interface Validator<T> {
    boolean validate(ValidatorContext context, String name, T data);
}
