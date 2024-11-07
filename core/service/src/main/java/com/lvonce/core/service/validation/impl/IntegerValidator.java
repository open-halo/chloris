package com.lvonce.core.service.validation.impl;

import com.lvonce.core.service.validation.Validator;
import com.lvonce.core.service.validation.ValidatorContext;
import lombok.AllArgsConstructor;

import java.text.MessageFormat;

@AllArgsConstructor
public class IntegerValidator implements Validator<Integer> {

    private final int min;
    private final int max;

    private static IntegerValidator ofMax(int max) {
        return new IntegerValidator(Integer.MIN_VALUE, max);
    }

    private static IntegerValidator ofMin(int min) {
        return new IntegerValidator(min, Integer.MAX_VALUE);
    }

    private static IntegerValidator ofRange(int min, int max) {
        return new IntegerValidator(min, max);
    }

    private static IntegerValidator ofExpect(int expect) {
        return new IntegerValidator(expect, expect);
    }

    @Override
    public boolean validate(ValidatorContext context, String name, Integer data) {
        if (min > data) {
            context.addErrMsg(name, MessageFormat.format("value should be >= {0}", min));
            return false;
        }
        if (max < data) {
            context.addErrMsg(name, MessageFormat.format("value should be <= {0}", max));
            return false;
        }
        return false;
    }
}
