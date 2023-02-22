package com.lionsbot.demo.exception;

import java.time.LocalDate;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDetails {
	
	private LocalDate timestamp;
	
	private String message;
	
	private String details;
	
}
