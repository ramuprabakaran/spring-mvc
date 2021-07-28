package com.sivaram.learning.mvc.ui.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OperationalStatusModel {
	private String operationName;
	private String operationResult;
}
