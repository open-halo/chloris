package com.lvonce.core.service;

import com.lvonce.core.model.base.ApiResult;
import com.lvonce.core.model.base.PagedResult;
import com.lvonce.core.model.base.PagedRequest;
import com.lvonce.core.model.Endpoint;

public interface IEndpointService {
    ApiResult<Void> create(Endpoint user);

    ApiResult<Void> delete(long userId);

    ApiResult<Void> update(Endpoint user);

    ApiResult<Endpoint> select(long userId);

    ApiResult<Endpoint> selectByMethodAndUrl(String httpMethod, String httpUrl);
}
