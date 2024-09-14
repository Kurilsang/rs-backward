package com.kuril.logindemo.exception;

import com.kuril.logindemo.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice(annotations = {RestController.class, Controller.class})//对所有加了RestController注解和Controller注解的类进行拦截操作
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {
    /**
     * 异常处理方法
     *
     * @return
     */
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)//拦截包含SQLIntegrityConstraintViolationException的异常
    public Result exceptionHandler(SQLIntegrityConstraintViolationException ex) {
        log.error(ex.getMessage());//将报错信息打印到控制台看看报的是什么错误"Duplicate entry '用户名字' for key '数据库里的名字'"

        if (ex.getMessage().contains("Duplicate entry")) {//如果抛出的是Duplicate entry开头的错误的话
            String[] split = ex.getMessage().split(" ");//根据空格找到用户的名称
            System.out.println(split);
            System.out.println(split[2]);
            String msg = split[2] + "已存在";//拿到用户名称并在后面加上"已存在"的提示
            return Result.error(msg);//将msg传到error里面，以便于报 错后提示给客户端

        }

        return Result.error("未知错误");//如果不是Duplicate entry开头的错误，直接返回一个未知错误就好了
    }


}