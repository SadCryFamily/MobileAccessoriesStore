package com.shopping.store.advice;

import com.shopping.store.exception.NothingToShowAccessoryException;
import com.shopping.store.exception.UnableToFindAccessoryException;
import com.shopping.store.util.ErrorsMapperUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class AccessoryAdvice {

    @ExceptionHandler({UnableToFindAccessoryException.class, NothingToShowAccessoryException.class})
    public ResponseEntity<Map<String, List<String>>> handleCreateExistingCustomerException(RuntimeException e) {
        List<String> errorsList = Collections.singletonList(e.getMessage());

        return new ResponseEntity<>(ErrorsMapperUtil.getErrorsMap(errorsList),
                new HttpHeaders(), HttpStatus.ALREADY_REPORTED);
    }

}
