/*
 * Copyright 1999-2011 Alibaba Group.
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package lucky.util.log.log4j;

import lucky.util.log.Logger;
import org.apache.log4j.Level;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;

public class Log4jLogger implements Logger {


    //修改为默认的配置信息
    private static final String FQCN = Log4jLoggerAdapter.class.getName();

    private final org.apache.log4j.Logger logger;

    public Log4jLogger(org.apache.log4j.Logger logger) {
        this.logger = logger;
    }

    public void trace(String msg) {
        logger.log(FQCN, Level.TRACE, msg, null);
    }

    public void trace(Throwable e) {
        logger.log(FQCN, Level.TRACE, e == null ? null : e.getMessage(), e);
    }

    public void trace(String msg, Throwable e) {
        logger.log(FQCN, Level.TRACE, msg, e);
    }

    @Override
    public void trace(String format, Object arg) {
        FormattingTuple ft = MessageFormatter.format(format, arg);
        trace(ft.getMessage());
    }

    @Override
    public void trace(String format, Object arg1, Object arg2) {
        FormattingTuple ft = MessageFormatter.format(format, arg1, arg2);
        trace(ft.getMessage());
    }

    @Override
    public void trace(String format, Object... args) {
        FormattingTuple ft = MessageFormatter.arrayFormat(format, args);
        trace(ft.getMessage());

    }

    public void debug(String msg) {
        logger.log(FQCN, Level.DEBUG, msg, null);
    }

    public void debug(Throwable e) {
        logger.log(FQCN, Level.DEBUG, e == null ? null : e.getMessage(), e);
    }

    public void debug(String msg, Throwable e) {
        logger.log(FQCN, Level.DEBUG, msg, e);
    }

    @Override
    public void debug(String format, Object arg) {
        FormattingTuple ft = MessageFormatter.format(format, arg);
        debug(ft.getMessage());
    }

    @Override
    public void debug(String format, Object arg1, Object arg2) {
        FormattingTuple ft = MessageFormatter.format(format, arg1, arg2);
        debug(ft.getMessage());
    }

    @Override
    public void debug(String format, Object... args) {
        FormattingTuple ft = MessageFormatter.arrayFormat(format, args);
        debug(ft.getMessage());
    }

    public void info(String msg) {
        logger.log(FQCN, Level.INFO, msg, null);
    }

    public void info(Throwable e) {
        logger.log(FQCN, Level.INFO, e == null ? null : e.getMessage(), e);
    }

    public void info(String msg, Throwable e) {
        logger.log(FQCN, Level.INFO, msg, e);
    }

    @Override
    public void info(String format, Object arg) {
        FormattingTuple ft = MessageFormatter.format(format, arg);
        info(ft.getMessage());
    }

    @Override
    public void info(String format, Object arg1, Object arg2) {
        FormattingTuple ft = MessageFormatter.format(format, arg1, arg2);
        info(ft.getMessage());
    }

    @Override
    public void info(String format, Object... args) {
        FormattingTuple ft = MessageFormatter.arrayFormat(format, args);
        info(ft.getMessage());
    }

    public void warn(String msg) {
        logger.log(FQCN, Level.WARN, msg, null);
    }

    public void warn(Throwable e) {
        logger.log(FQCN, Level.WARN, e == null ? null : e.getMessage(), e);
    }

    public void warn(String msg, Throwable e) {
        logger.log(FQCN, Level.WARN, msg, e);
    }

    @Override
    public void warn(String format, Object arg) {
        FormattingTuple ft = MessageFormatter.format(format, arg);
        warn(ft.getMessage());
    }

    @Override
    public void warn(String format, Object... args) {
        FormattingTuple ft = MessageFormatter.arrayFormat(format, args);
        error(ft.getMessage());
    }

    @Override
    public void warn(String format, Object arg1, Object arg2) {
        FormattingTuple ft = MessageFormatter.format(format, arg1, arg2);
        error(ft.getMessage());
    }

    public void error(String msg) {
        logger.log(FQCN, Level.ERROR, msg, null);
    }

    public void error(Throwable e) {
        logger.log(FQCN, Level.ERROR, e == null ? null : e.getMessage(), e);
    }

    public void error(String msg, Throwable e) {
        logger.log(FQCN, Level.ERROR, msg, e);
    }

    @Override
    public void error(String format, Object arg) {
        FormattingTuple ft = MessageFormatter.format(format, arg);
        error(ft.getMessage());
    }

    @Override
    public void error(String format, Object arg1, Object arg2) {
        FormattingTuple ft = MessageFormatter.format(format, arg1, arg2);
        error(ft.getMessage());
    }

    @Override
    public void error(String format, Object... args) {
        FormattingTuple ft = MessageFormatter.arrayFormat(format, args);
        error(ft.getMessage());
    }

    public boolean isTraceEnabled() {
        return logger.isTraceEnabled();
    }

    public boolean isDebugEnabled() {
        return logger.isDebugEnabled();
    }

    public boolean isInfoEnabled() {
        return logger.isInfoEnabled();
    }

    public boolean isWarnEnabled() {
        return logger.isEnabledFor(Level.WARN);
    }

    public boolean isErrorEnabled() {
        return logger.isEnabledFor(Level.ERROR);
    }

}