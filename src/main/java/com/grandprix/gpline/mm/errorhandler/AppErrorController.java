package com.grandprix.gpline.mm.errorhandler;

import com.grandprix.gpline.mm.utils.Log;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;


@RestController
public class AppErrorController implements ErrorController {

    private static final String PATH = "/error";

    @RequestMapping(PATH)
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        Object requestURI = request.getAttribute(RequestDispatcher.ERROR_REQUEST_URI);
        Object exception = request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
        Log.error("Incorrect request. HTTP status: " + status +" requestURI: " + requestURI + " exception: " + exception);
        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());
            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                return "Извините, но такой запрос не может быть обрабатан (404)";
            }
            else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "Извините, произошла ошибка приложения (500)";
            }
        }
        return "Непонятная ошибка (?)";
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}