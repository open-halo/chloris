package com.lvonce.api.rest;

import com.lvonce.core.model.Endpoint;
import com.lvonce.core.model.base.ApiResult;
import com.lvonce.core.model.base.PagedRequest;
import com.lvonce.core.model.base.PagedResult;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

@RequestMapping("/endpoints")
public interface IEndpointResource {

    @PostMapping
    ApiResult<Void> create(@Valid Endpoint user);

    @DeleteMapping("/{endpointId}")
    ApiResult<Void> delete(@PathVariable("endpointId") long endpointId);

    @PatchMapping
    ApiResult<Void> update(Endpoint endpoint);

    @GetMapping("/p/{endpointId}")
    ApiResult<Endpoint> select(@PathVariable("endpointId") long endpointId);

    @GetMapping("/q")
    ApiResult<Endpoint> selectByMethodAndUrl(@RequestParam("method") String httpMethod,
                                             @RequestParam("url") String httpUrl);

    @GetMapping("/index")
    ApiResult<PagedResult<Endpoint>> search(PagedRequest<String> nameSearch);

}
