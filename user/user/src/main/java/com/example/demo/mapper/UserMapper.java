package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.dto.UserDTO;

@Mapper
public interface UserMapper {
	public UserDTO findById(int id);
	
	public int withdraw(int id); 
}
