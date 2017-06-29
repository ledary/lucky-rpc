package com.lucky.db.executor.transaction;

import com.lucky.db.executor.DataBase;
import com.lucky.db.executor.Executor;

import javax.sql.DataSource;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @Author:chaoqiang.zhou
 * @Description:同时有了所有得方法,后期考虑datasource如何缓存起来 一定要保证用的是一个数据库得连接操作
 * @Date:Create in 15:31 2017/6/29
 */
public class TransactionExecutorImpl extends DataBase implements TransactionExecutor {
    public DataSource dataSource;
    public TransactionTemplate transactionTemplate;

    public TransactionExecutorImpl(DataSource dataSource) {
        super(dataSource);
        this.dataSource = dataSource;
        this.transactionTemplate = new TransactionTemplate(dataSource);
    }

    @Override
    public Executor begin() {
        return new TransactionExecutorImpl(dataSource);
    }

    @Override
    public void begin(Consumer<Executor> action) {
        this.transactionTemplate.execute(action);
    }

    @Override
    public <T> T begin(Function<Executor, T> func) {
        return this.transactionTemplate.execute(func);
    }


}
