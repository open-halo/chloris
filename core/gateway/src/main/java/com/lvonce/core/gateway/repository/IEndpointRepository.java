package com.lvonce.core.gateway.repository;

import com.lvonce.core.model.base.ApiResult;
import com.lvonce.core.model.base.PagedResult;
import com.lvonce.core.model.base.PagedRequest;
import com.lvonce.core.model.Endpoint;

public interface IEndpointRepository {
    ApiResult<Void> create(Endpoint endpoint);

    ApiResult<Void> delete(long endpointId);

    ApiResult<Void> update(Endpoint endpoint);

    ApiResult<Endpoint> select(long endpointId);

    ApiResult<Endpoint> selectByMethodAndUrl(String httpMethod, String httpUrl);

}
