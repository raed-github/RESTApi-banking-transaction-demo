package com.rsaad.transaction.exceptions.validation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Builder
@Setter
@Getter
@AllArgsConstructor
public class FieldValidationError {
	private String message;
	private LocalDate date;
}
