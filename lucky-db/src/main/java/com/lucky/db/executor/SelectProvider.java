package com.lucky.db.executor;

import com.lucky.db.executor.context.SelectContext;
import com.lucky.db.executor.result.BuildResult;
import com.lucky.db.executor.result.SelectResult;
import com.lucky.db.sqlbuilder.SQL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author:chaoqiang.zhou
 * @Description:
 * @Date:Create in 18:04 2017/6/26
 */
public class SelectProvider implements SelectContext {


    public ConnectionManager manager;
    private SQL sqlBuilder = new SQL();
    private List<String> columns = new ArrayList<>();
    private List<Object> args = new ArrayList<>();


    public SelectProvider(String column, ConnectionManager manager) {
        this.columns.add(column);
        this.manager = manager;
    }

    public SelectProvider(ConnectionManager manager, String... columns) {
        this.columns.addAll(Arrays.asList(columns));
        this.manager = manager;
    }

    @Override
    public SelectContext SELECT(String columns) {
        this.sqlBuilder.SELECT(columns);
        return this;
    }

    @Override
    public SelectContext SELECT_DISTINCT(String columns) {
        this.sqlBuilder.SELECT_DISTINCT(columns);
        return this;
    }

    @Override
    public SelectContext COUNT(String fieldName) {
        String field = "count(" + fieldName + ")";
        this.sqlBuilder.SELECT(field);
        return this;
    }

    @Override
    public SelectContext FROM(String table) {
        this.sqlBuilder.FROM(table);
        return this;
    }

    @Override
    public SelectContext JOIN(String join) {
        this.sqlBuilder.JOIN(join);
        return this;
    }

    @Override
    public SelectContext INNER_JOIN(String join) {
        this.sqlBuilder.INNER_JOIN(join);
        return this;
    }

    @Override
    public SelectContext LEFT_OUTER_JOIN(String join) {
        this.sqlBuilder.LEFT_OUTER_JOIN(join);
        return this;
    }

    @Override
    public SelectContext RIGHT_OUTER_JOIN(String join) {
        this.sqlBuilder.RIGHT_OUTER_JOIN(join);
        return this;

    }

    @Override
    public SelectContext OUTER_JOIN(String join) {
        this.sqlBuilder.OUTER_JOIN(join);
        return this;
    }

    //只有where的时候才会有参数信息
    @Override
    public SelectContext WHERE(String conditions, ConditionType type, Object value) {

        this.sqlBuilder.WHERE(conditions + "" + type.value + " ?");
        this.args.add(value);
        return this;
    }

    @Override
    public SelectContext OR() {
        this.sqlBuilder.OR();
        return this;
    }

    @Override
    public SelectContext AND() {
        this.sqlBuilder.AND();
        return this;
    }

    @Override
    public SelectContext GROUP_BY(String... columns) {
        for (String column : columns) {
            this.sqlBuilder.GROUP_BY(column);
        }
        return this;
    }

    @Override
    public SelectContext HAVING(String conditions) {
        this.sqlBuilder.HAVING(conditions);
        return this;
    }

    @Override
    public SelectContext ORDER_BY(String columns, Sorter sorter) {
        this.sqlBuilder.ORDER_BY(columns + sorter.name());
        return this;
    }

    @Override
    public SelectContext ORDER_BY(String columns) {
        this.sqlBuilder.ORDER_BY(columns);
        return this;
    }

    @Override
    public SelectContext LIMIT(String... columns) {
        this.sqlBuilder.LIMIT(columns);
        return this;
    }

    @Override
    public BuildResult print() {
        columns.forEach(data -> {
            this.sqlBuilder.SELECT(data);
        });
        return new BuildResult(args, this.sqlBuilder.toString());
    }


    @Override
    public SelectResult result() {
        //不需要加锁配置
        return this.result(LockMode.NONE);
    }


    /***
     * for update的模式操作
     * @param lockMode
     * @return
     */
    @Override
    public SelectResult result(LockMode lockMode) {
        BuildResult buildResult = print();
        if (lockMode == LockMode.SHARED) {
            buildResult.setSql(buildResult.getSql() + " lock in share mode");
        } else if (lockMode == LockMode.EXCLUSIVE) {
            buildResult.setSql(buildResult.getSql() + " for update");
        }
        return DbUtil.executeQuery(this.manager, buildResult.getSql(), buildResult.getArgs());
    }
}
