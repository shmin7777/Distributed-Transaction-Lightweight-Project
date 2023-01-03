package com.example.order.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.order.dto.OrderDTO;
import com.example.order.global.OrderException;
import com.example.order.service.OrderService;

@RestController
@RequestMapping("/")
public class OrderController {
	@Autowired
	private OrderService orderService;

	@PostMapping("/order")
	public void order(@RequestHeader String globalId, @RequestBody String id) {
		System.out.println("order controller 들어옴");
		OrderDTO orderDTO = new OrderDTO();

		System.out.println("id ::: " + id);

		orderDTO.setUserId(Integer.parseInt(id));
		orderDTO.setTrId(globalId);

		System.out.println("trID ::: " + globalId);
		
		Integer result  = orderService.confirm(orderDTO);
		System.out.println("result is : " + result);
	}

	@PostMapping("/test")
	public String test(@RequestBody String event) {
		System.out.println(event.toString());

		return event.toString();
	}
}