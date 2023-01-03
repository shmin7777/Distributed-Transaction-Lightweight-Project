package com.example.demo.service;

import org.json.simple.parser.ParseException;

import com.example.demo.dto.UserDTO;

public interface UserService {
	public UserDTO findById(int id);
	
	public void withdraw(int id, String globalId) throws RuntimeException ;
}
