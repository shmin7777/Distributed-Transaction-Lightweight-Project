package com.example.order.service;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.order.dto.OrderDTO;
import com.example.order.listener.AfterTransactionEvent;
import com.example.order.listener.BeforeTransactionEvent;
import com.example.order.mapper.OrdersMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
	private final OrdersMapper ordersMapper;
	private final ApplicationEventPublisher applicationEventPublisher;
	private final HttpConnection httpConnection;
	boolean flag = true;
	@Override
	@Async
	@Transactional
	public Integer confirm(OrderDTO orderDTO) {
		int isInserted = ordersMapper.insert(orderDTO);
		isInserted = 0;
		if (isInserted != 1)
			flag = false;
		BeforeTransactionEvent beforeTransactionEvent = () -> {
			String isCommited = httpConnection.sendREST("http://211.105.21.17:1111/prepared", Boolean.toString(flag),
					orderDTO.getTrId());
			if(isCommited.equals("false")) throw new RuntimeException();		
		};
		AfterTransactionEvent afterTransactionEvent = new AfterTransactionEvent() {
			@Override
			public void callback() {
				log.info("transaction end");
			}
			@Override
			public void completed() {
				log.info("committed");
			}
		};
		applicationEventPublisher.publishEvent(beforeTransactionEvent);
		applicationEventPublisher.publishEvent(afterTransactionEvent);
		return isInserted;
	}
}
