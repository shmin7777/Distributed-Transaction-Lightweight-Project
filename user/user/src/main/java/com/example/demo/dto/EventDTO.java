package com.example.demo.dto;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class EventDTO {
	private boolean commit;
	private String txId;
}
