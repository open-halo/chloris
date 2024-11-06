package com.lvonce.infra.persistent.gateway;

import com.lvonce.infra.persistent.mapper.EndpointMapper;
import jakarta.inject.Named;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.lvonce.core.model.base.ApiResult;
import com.lvonce.core.gateway.repository.IEndpointRepository;
import com.lvonce.infra.persistent.entity.Endpoint;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@AllArgsConstructor
public class EndpointRepository implements IEndpointRepository {

    private EndpointMapper endpointMapper;

    @Mapper
    interface EndpointRepositoryMapper {
        EndpointRepositoryMapper INSTANCE = Mappers.getMapper(EndpointRepositoryMapper.class);

        com.lvonce.core.model.Endpoint entityToModel(Endpoint endpoint);

        Endpoint domainToEntity(com.lvonce.core.model.Endpoint endpoint);

    }

    @Override
    public ApiResult<Void> create(com.lvonce.core.model.Endpoint endpoint) {
        Endpoint endpointEntity = EndpointRepositoryMapper.INSTANCE.domainToEntity(endpoint);
        endpointMapper.insert(endpointEntity);
        return ApiResult.ofSuccess();
    }

    @Override
    public ApiResult<Void> delete(long endpointId) {
        endpointMapper.deleteById(endpointId);
        return ApiResult.ofSuccess();
    }

    @Override
    public ApiResult<Void> update(com.lvonce.core.model.Endpoint endpoint) {
        Endpoint endpointEntity = EndpointRepositoryMapper.INSTANCE.domainToEntity(endpoint);
        endpointMapper.update(endpointEntity);
        return ApiResult.ofSuccess();
    }

    @Override
    public ApiResult<com.lvonce.core.model.Endpoint> select(long endpointId) {
        Endpoint endpoint = endpointMapper.selectOneById(endpointId);
        com.lvonce.core.model.Endpoint endpointDomain = EndpointRepositoryMapper.INSTANCE
            .entityToModel(endpoint);
        return ApiResult.ofSuccess(endpointDomain);
    }

    @Override
    public ApiResult<com.lvonce.core.model.Endpoint> selectByMethodAndUrl(String httpMethod,
                                                                          String httpUrl) {
        log.info("selectByMethodAndUrl({}, {})", httpMethod, httpUrl);
        Endpoint endpoint = endpointMapper.selectOneByMethodAndUrl(httpMethod, httpUrl);
        com.lvonce.core.model.Endpoint endpointDomain = EndpointRepositoryMapper.INSTANCE
            .entityToModel(endpoint);
        return ApiResult.ofSuccess(endpointDomain);
    }
}
