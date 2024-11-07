package com.lvonce.core.service.validation.impl;

import com.lvonce.core.service.validation.Validator;
import com.lvonce.core.service.validation.ValidatorContext;
import lombok.AllArgsConstructor;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
public class IntegerEnumValidator implements Validator<Integer> {

    private final Set<Integer> enumSet;

    public static IntegerEnumValidator ofIntEnum(int[] intEnums) {
        Set<Integer> enumSet = new HashSet<>();
        Arrays.stream(intEnums).forEach(enumSet::add);
        return new IntegerEnumValidator(enumSet);
    }

    public static IntegerEnumValidator ofIntEnum(ArrayList<Integer> intEnums) {
        Set<Integer> enumSet = new HashSet<>(intEnums);
        return new IntegerEnumValidator(enumSet);
    }

    @Override
    public boolean validate(ValidatorContext context, String name, Integer data) {
        if (!enumSet.contains(data)) {
            context.addErrMsg(name, MessageFormat.format("value should be enum of  {0}", enumSet));
            return false;
        }

        return true;
    }
}
