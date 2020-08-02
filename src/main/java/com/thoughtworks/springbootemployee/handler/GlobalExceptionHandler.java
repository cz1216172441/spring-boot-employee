package com.thoughtworks.springbootemployee.handler;

import com.thoughtworks.springbootemployee.exception.ArgumentNotValidException;
import com.thoughtworks.springbootemployee.exception.CompanyNameExistedException;
import com.thoughtworks.springbootemployee.exception.CompanyNotFoundException;
import com.thoughtworks.springbootemployee.exception.EmployeeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CompanyNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String companyNotFoundExceptionHandler() {
        return "company not found";
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String employeeNotFoundExceptionHandler() {
        return "employee not found";
    }

    @ExceptionHandler(CompanyNameExistedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<String> companyNameExistedExceptionHandler(CompanyNameExistedException e) {
        List<String> errors = new ArrayList<>();
        errors.add(e.getMessage());
        return errors;
    }

    @ExceptionHandler(ArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<String> argumentNotValidExceptionHandler(ArgumentNotValidException e) {
        List<String> errors = new ArrayList<>();
        errors.add(e.getMessage());
        return errors;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<String> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        return e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> String.format("%s: %s", fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList());
    }

}
