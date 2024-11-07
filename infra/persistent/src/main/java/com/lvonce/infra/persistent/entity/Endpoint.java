package com.lvonce.infra.persistent.entity;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.Table;
import lombok.Data;

@Data
@Table("endpoint")
public class Endpoint {
    @Id
    private Long   id;

    private String httpMethod;

    private String httpUrl;

    private String sqlTemplate;
}
