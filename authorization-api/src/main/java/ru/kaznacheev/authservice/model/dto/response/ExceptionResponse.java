package ru.kaznacheev.authservice.model.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ExceptionResponse {

    private final int status;

    private final String error;

    private final String message;

}
