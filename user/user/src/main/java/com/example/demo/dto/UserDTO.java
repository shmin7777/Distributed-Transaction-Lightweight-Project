package com.example.demo.dto;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class UserDTO {
	private int uId;
	private String uName;
	private int uBalance;
}
