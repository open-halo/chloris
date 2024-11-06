package com.lvonce.interceptor;

import com.lvonce.core.model.Endpoint;
import com.lvonce.core.model.base.ApiResult;
import com.lvonce.core.service.impl.EndpointService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.PrintWriter;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component
@AllArgsConstructor
public class SqlRunnerInterceptor implements HandlerInterceptor {

    private SqlTemplateService sqlTemplateService;

    private EndpointService endpointService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String httpMethod = request.getMethod();
        String httpUrl = request.getRequestURI();
        if (httpMethod.equalsIgnoreCase(request.getMethod()) && httpUrl.startsWith("/api")) {

            // 获取所有查询参数
            Map<String, String[]> parameterMap = request.getParameterMap();
            Map<String, String> queryParameters = parameterMap.entrySet().stream()
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            entry -> String.join(",", entry.getValue())
                    ));
            log.info("query map: {}", queryParameters);

            ApiResult<Endpoint> endpointResult = endpointService.selectByMethodAndUrl(httpMethod, httpUrl);
            Endpoint endpoint = endpointResult.unwrap();
            log.info("endpoint: {}", endpoint);

            sqlTemplateService.executeSqlFromTemplate(endpoint.getSqlTemplate(), queryParameters);



            // 返回 "hello" 并中断请求
            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("text/plain");
            PrintWriter writer = response.getWriter();
            writer.print("hello");
            writer.flush();
            writer.close();
            return false;
        }
        return true;
    }
}
