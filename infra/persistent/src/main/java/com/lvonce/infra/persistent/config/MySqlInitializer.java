package com.lvonce.infra.persistent.config;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.lvonce.infra.persistent.config.base.SqlInitExecutor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Slf4j
@Component
@Profile("dev-mysql")
@AllArgsConstructor
public class MySqlInitializer implements CommandLineRunner {

    private final SqlInitExecutor initExecutor;

    private final DataSource      dataSource;

    @Override
    public void run(String... args) throws Exception {
        initExecutor.execute(dataSource, "sql/mysql");
    }

}
