package com.ruoyi.framework.interceptor;

import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.framework.datasource.DynamicDataSourceContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.util.Properties;

/**
 * @author as2i
 * @date 2022/8/27 14:25
 */

@Slf4j
@Component
@Intercepts({@Signature(method = "prepare", type = StatementHandler.class, args = {Connection.class, Integer.class}),
        @Signature(method = "query", type = Executor.class, args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class})})
public class SqliteDataSourceInterceptor implements Interceptor {

    @Override
    public Object plugin(Object target) {
        // 调用插件
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }

    @Override
    public Object intercept(Invocation invocation) throws Exception {
        // 该方法写入自己的逻辑
        if (invocation.getTarget() instanceof StatementHandler) {
            String dataSoureType = DynamicDataSourceContextHolder.getDataSourceType();
            // judge dataSource type ,because sqlite can't use internal.core_project this express
            // so we need to add "" for it or delete this 'internal.'
            /*if (DataSourceType.SQLITE.name().equals(dataSoureType)) {
                RoutingStatementHandler handler = (RoutingStatementHandler) invocation.getTarget();
                StatementHandler delegate = (StatementHandler) ReflectUtil.getFieldValue(handler, "delegate");
                BoundSql boundSql = delegate.getBoundSql();
                String sql = boundSql.getSql();
                sql = sql.replace("internal.", " ");
                ReflectUtil.setFieldValue(boundSql, "sql", sql);
            }*/
        }
        // SQL execute start time
        long startTimeMillis = System.currentTimeMillis();
        // get execute result
        Object proceedReslut = invocation.proceed();
        // SQL execute end time
        long endTimeMillis = System.currentTimeMillis();
        log.debug("<< ==== sql execute runnung time：{} millisecond ==== >>", (endTimeMillis - startTimeMillis));
        log.info("==================================================");
        return proceedReslut;
    }
}
