package com.joe.rbac.exception;

import com.joe.rbac.common.ServerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.security.auth.login.AccountExpiredException;
import java.nio.file.AccessDeniedException;
import java.sql.SQLException;

@Slf4j
@RestControllerAdvice
public class BaseExceptionHandler {
    /**
     * 处理自定义异常
     */
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ServerResponse> handleRRException(BaseException e) {
        log.error(e.getMessage(),e);
        //BaseException:状态码为HttpStatus的状态码
        ServerResponse response = ServerResponse.createByErrorMessage(e.getMsg());
        return new ResponseEntity<ServerResponse>(response, HttpStatus.valueOf(e.getCode()));
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ServerResponse> handlerNoFoundException(Exception e) {
        log.error(e.getMessage(), e);
        ServerResponse response = ServerResponse.createByErrorMessage("路径不存在，请检查路径是否正确");
        return new ResponseEntity<ServerResponse>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<ServerResponse> handleDuplicateKeyException(DuplicateKeyException e) {
        log.error(e.getMessage(), e);
        ServerResponse response = ServerResponse.createByErrorMessage("数据库中已存在该记录");
        return new ResponseEntity<ServerResponse>(response, HttpStatus.MULTIPLE_CHOICES);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ServerResponse> handleAuthorizationException(AccessDeniedException e) {
        log.error(e.getMessage(), e);
        ServerResponse response = ServerResponse.createByErrorMessage("没有权限，请联系管理员授权");
        return new ResponseEntity<ServerResponse>(response, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(AccountExpiredException.class)
    public ServerResponse handleAccountExpiredException(AccountExpiredException e) {
        log.error(e.getMessage(), e);
        return ServerResponse.createByErrorMessage(e.getMessage());
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ServerResponse handleUsernameNotFoundException(UsernameNotFoundException e) {
        log.error(e.getMessage(), e);
        return ServerResponse.createByErrorMessage(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ServerResponse handleException(Exception e) {
        log.error(e.getMessage(), e);
        return ServerResponse.createByError();
    }

    @ExceptionHandler(SQLException.class)
    public ServerResponse handleSQLException(SQLException e) {
        log.error(e.getMessage(), e);
        return ServerResponse.createByError();
    }
}
