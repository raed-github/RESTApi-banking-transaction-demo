package com.rsaad.transaction.exceptions.validation;

import lombok.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Setter
@Getter
public class ErrorResult {
	private final List<FieldValidationError> fieldErrors = new ArrayList<>();
}
