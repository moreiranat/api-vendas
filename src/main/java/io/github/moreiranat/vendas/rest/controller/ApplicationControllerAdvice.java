package io.github.moreiranat.vendas.rest.controller;

import io.github.moreiranat.vendas.exception.RegraNegocioException;
import io.github.moreiranat.vendas.rest.ApiErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(RegraNegocioException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) //codigo de status 400
    public ApiErrors handleRegraNegocioException(RegraNegocioException exception) {
        String mensagemErro = exception.getMessage();
        return new ApiErrors(mensagemErro);

    }


}
