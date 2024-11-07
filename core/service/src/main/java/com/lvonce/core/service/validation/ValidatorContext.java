package com.lvonce.core.service.validation;

import cn.hutool.json.JSONUtil;
import com.lvonce.core.model.base.ApiResult;

import java.util.HashMap;
import java.util.Map;

public class ValidatorContext {
    private final Map<String, String> errMsgMap = new HashMap<>();

    public void addErrMsg(String field, String errMsg) {
        this.errMsgMap.put(field, errMsg);
    }

    public ApiResult<Void> toApiResult() {
        if (errMsgMap.isEmpty()) {
            return ApiResult.ofSuccess();
        }
        return ApiResult.ofFailure(400, JSONUtil.toJsonStr(errMsgMap));
    }
}
