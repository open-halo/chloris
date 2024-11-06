package com.lvonce.core.service.impl;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import com.lvonce.common.annotations.HaloSentinelResource;
import com.lvonce.core.model.base.ApiResult;
import com.lvonce.core.model.Endpoint;
import com.lvonce.core.gateway.repository.IEndpointRepository;
import com.lvonce.core.service.IEndpointService;

@Slf4j
@Named("endpointService")
public class EndpointService implements IEndpointService {

    @Inject
    private IEndpointRepository endpointRepository;

    @Override
    public ApiResult<Void> create(Endpoint user) {
        return endpointRepository.create(user);
    }

    @Override
    public ApiResult<Void> delete(long userId) {
        return endpointRepository.delete(userId);
    }

    @Override
    public ApiResult<Void> update(Endpoint user) {
        return endpointRepository.update(user);
    }

    //    @HaloSentinelResource("query-user-by-id")
    @Override
    public ApiResult<Endpoint> select(long userId) {
        log.info("select({})", userId);
        return endpointRepository.select(userId);
    }

    @Override
    public ApiResult<Endpoint> selectByMethodAndUrl(String httpMethod, String httpUrl) {
        return endpointRepository.selectByMethodAndUrl(httpMethod, httpUrl);
    }
}
