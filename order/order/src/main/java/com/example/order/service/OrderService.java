package com.example.order.service;

import com.example.order.dto.OrderDTO;
import com.example.order.global.OrderException;

public interface OrderService {
	// 주문 체결
	public Integer confirm(OrderDTO requestDTO);
}
