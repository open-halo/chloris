package com.lvonce.api.adapter.rest;

//import jakarta.inject.Named;
import com.lvonce.api.rest.IEndpointResource;
import com.lvonce.core.service.impl.EndpointService;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.lvonce.core.model.base.ApiResult;
import com.lvonce.core.model.base.PagedResult;
import com.lvonce.core.model.base.PagedRequest;
import com.lvonce.core.model.Endpoint;
import com.lvonce.core.service.IEndpointService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
public class EndpointController implements IEndpointResource {

    private EndpointService endpointService;

    @Override
    public ApiResult<Void> create(Endpoint endpoint) {
        return endpointService.create(endpoint);
    }

    @Override
    public ApiResult<Void> delete(long endpointId) {
        return endpointService.delete(endpointId);
    }

    @Override
    public ApiResult<Void> update(Endpoint endpoint) {
        return endpointService.update(endpoint);
    }

    @Override
    public ApiResult<Endpoint> select(long endpointId) {
        log.info("select({})", endpointId);
        return endpointService.select(endpointId);
    }

    @Override
    public ApiResult<Endpoint> selectByMethodAndUrl(String httpMethod, String httpUrl) {
        log.info("selectByMethodAndUrl({} {})", httpMethod, httpUrl);
        return endpointService.selectByMethodAndUrl(httpMethod, httpUrl);
    }

    @Override
    public ApiResult<PagedResult<Endpoint>> search(PagedRequest<String> nameSearch) {
        return null;
    }
}
