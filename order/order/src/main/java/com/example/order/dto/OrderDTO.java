package com.example.order.dto;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class OrderDTO {
//	private int id;
	private int userId;
	private String trId;
}
