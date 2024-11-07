package com.lvonce.interceptor;

import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.lvonce.core.model.base.ApiResult;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.builder.StaticSqlSource;
import org.apache.ibatis.mapping.*;
import org.apache.ibatis.scripting.LanguageDriver;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.scripting.xmltags.DynamicSqlSource;
import org.apache.ibatis.scripting.xmltags.XMLLanguageDriver;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@AllArgsConstructor
public class SqlTemplateService {

    //    private Configuration mybatisConfiguration;
    private SqlSessionFactory sqlSessionFactory;

    private DataSource        dataSource;

    public String executeSqlFromTemplate(String template, Map<String, Object> parameters) {

        // 创建 SqlSource
        Configuration configuration = sqlSessionFactory.getConfiguration();
        LanguageDriver languageDriver = configuration.getLanguageDriver(XMLLanguageDriver.class);

        SqlSource sqlSource = languageDriver.createSqlSource(configuration, template, Map.class);
        // 创建 BoundSql
        BoundSql boundSql = sqlSource.getBoundSql(parameters);

        String statementId = "com.lvonce.interceptor.SqlTemplateService.generic";
        MappedStatement.Builder statementBuilder = new MappedStatement.Builder(configuration,
            statementId, sqlSource, SqlCommandType.SELECT);
        MappedStatement mappedStatement = statementBuilder.build();

        // 创建 ParameterHandler
        DefaultParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement,
            parameters, boundSql);

        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(boundSql.getSql());
            parameterHandler.setParameters(ps);
            ResultSet resultSet = ps.executeQuery();
            String jsonResult = convertResultSetToJson(resultSet);
            log.info("json result: {}", jsonResult);
            return jsonResult;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to build SQL from template", e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                log.error("connection close error: {}", e.getMessage());
            }
        }
    }

    public static String convertResultSetToJson(ResultSet resultSet) throws SQLException {
        //        ObjectMapper objectMapper = new ObjectMapper();
        //        ArrayNode resultArray = objectMapper.createArrayNode();
        ArrayList<Map<String, Object>> values = new ArrayList<>();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();

        while (resultSet.next()) {
            //            ObjectNode rowNode = objectMapper.createObjectNode();
            Map<String, Object> valueMap = new HashMap<>();
            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnName(i);
                Object columnValue = resultSet.getObject(i);
                //                rowNode.put(columnName, columnValue.toString());
                valueMap.put(columnName, columnValue);
            }
            values.add(valueMap);
        }
        ApiResult<ArrayList<Map<String, Object>>> apiResult = ApiResult.ofSuccess(values);
        return JSONUtil.toJsonStr(apiResult);
    }
}