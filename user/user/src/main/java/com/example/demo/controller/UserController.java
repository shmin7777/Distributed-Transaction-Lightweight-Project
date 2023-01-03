package com.example.demo.controller;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserDTO;
import com.example.demo.service.HttpConnection;
import com.example.demo.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;
	private final HttpConnection connection;
	
	@PostMapping("/orders")
	public JSONObject orders(@RequestBody int id) {
		JSONObject jsonObject = new JSONObject();
		UserDTO userDTO = userService.findById(id);
		
		try{
		jsonObject = connection.sendGet("http://211.105.21.17:1111/trId", null); //global Id 받아오기
			userService.withdraw(id, jsonObject.get("trId").toString());
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("잡았따!");
		}
		return jsonObject;
	}
	
}
