package com.lvonce.core.service.validation.impl;

import com.lvonce.core.service.validation.Validator;
import com.lvonce.core.service.validation.ValidatorContext;

import java.text.MessageFormat;

public class RequiredValidator implements Validator<Object> {
    @Override
    public boolean validate(ValidatorContext context, String name, Object data) {
        if (data == null) {
            context.addErrMsg(name, MessageFormat.format("field {0} must be required", name));
            return false;
        }
        return true;
    }
}
