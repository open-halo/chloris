package com.lvonce.infra.persistent.mapper;

import com.lvonce.infra.persistent.entity.Endpoint;
import com.mybatisflex.core.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface EndpointMapper extends BaseMapper<Endpoint> {
    @Select("SELECT * FROM endpoint where http_method = #{httpMethod} AND http_url = #{httpUrl} LIMIT 1")
    @Results({ @Result(property = "id", column = "id"),
            @Result(property = "httpMethod", column = "http_method"),
            @Result(property = "httpUrl", column = "http_url"),
            @Result(property = "sqlTemplate", column = "sql_template") })
    Endpoint selectOneByMethodAndUrl(@Param("httpMethod") String httpMethod,
                                     @Param("httpUrl") String httpUrl);
}
