package com.grandprix.gpline.mm.errorhandler;

import lombok.Data;

/**
 * Обрабатываемое исключение с текстом, выдаваемым пользователю
 * Пример:
 *        throw new AppException("Некорректный порядок сортировки.");
 *
 */
@Data
public class AppException extends RuntimeException {

    private String errMsg;
    private Exception exception;
    private String errCode;

    public AppException(String msg) {
        this.errMsg = msg;
    }

    public AppException(String msg, Exception ex) {
        this.errMsg = msg;
        this.exception = ex;
    }
}
