package pro.walkin.base.boot.handle;

import cn.dev33.satoken.exception.NotLoginException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import pro.walkin.framework.exception.code.ExceptionCode;
import pro.walkin.framework.web.Result;

import java.sql.SQLException;

/**
 * 全局异常处理
 */
@Slf4j
public abstract class AbstractGlobalExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.OK)
    public Result<?> nullPointerException(Exception ex) {
        log.error("NullPointerException:{}", ex.getMessage(), ex);
        return Result.fail(ExceptionCode.NULL_POINT_EX.getMsg());
    }

    @ExceptionHandler(SQLException.class)
    @ResponseStatus(HttpStatus.OK)
    public Result<?> sqlException(SQLException ex) {
        log.error("SQLException:{}", ex.getMessage());
        return Result.fail(ExceptionCode.SQL_EX.getMsg());
    }

    @ExceptionHandler(NotLoginException.class)
    @ResponseStatus(HttpStatus.OK)
    public Result<?> notLoginException(NotLoginException notLoginException) {
        return Result.fail(notLoginException.getMessage());
    }

    @ExceptionHandler(InvalidDataAccessResourceUsageException.class)
    @ResponseStatus(HttpStatus.OK)
    public Result<?> invalidDataAccessResourceUsageException(InvalidDataAccessResourceUsageException ex) {
        log.error("InvalidDataAccessResourceUsageException:{}", ex.getMessage());
        return Result.fail("DAO has exception");
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public Result<?> exception(Exception ex) {
        log.error(ex.getMessage(), ex);
        return Result.fail(ex.getMessage());
    }

}
