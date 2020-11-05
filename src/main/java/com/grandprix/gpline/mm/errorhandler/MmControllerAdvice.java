package com.grandprix.gpline.mm.errorhandler;

import com.grandprix.gpline.mm.utils.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Обработчик исключений приложения
 */
@ControllerAdvice
public class MmControllerAdvice extends ResponseEntityExceptionHandler {

    /**
     * Необрабатываемое исключение
     * @param eх
     * @return
     */
    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<String> handleRunTimeException(RuntimeException eх) {
        Log.error("Необрабатываемое исключение: " + eх.getMessage(), eх);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Извините, произошла ошибка ("+ eх.getClass().getName() +" : " + eх.getMessage() + ")");
    }

    /**
     * Обрабатываемое исключение
     * @param eх
     * @return
     */
    @ExceptionHandler({AppException.class})
    public ResponseEntity<String> handleApplicationException(AppException eх) {
        String errMsg = eх.getErrMsg();
        Log.error("Ошибка приложения. " + errMsg, eх.getException());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errMsg);
    }
}
