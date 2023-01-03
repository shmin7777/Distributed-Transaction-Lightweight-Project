package com.example.order.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.order.dto.OrderDTO;

@Mapper
public interface OrdersMapper {
	public int insert(OrderDTO orderDTO);
}
