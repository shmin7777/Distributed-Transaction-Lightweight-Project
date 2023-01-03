package com.example.demo.service;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.UserDTO;
import com.example.demo.listener.AfterTransactionEvent;
import com.example.demo.listener.BeforeTransactionEvent;
import com.example.demo.listener.BeforeTransactionEventImpl;
import com.example.demo.mapper.UserMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

	private final UserMapper userMapper;
	private final ApplicationEventPublisher applicationEventPublisher;
	private final HttpConnection httpConnection;
	boolean flag = true; //commit여부 확인하는 bool 변수
	String canCommit ="";
	@Override
	public UserDTO findById(int id) {
		
		return userMapper.findById(id);
	}

	@Async
	@Override
	@Transactional(rollbackFor = {Exception.class,RuntimeException.class})
	public void withdraw(int id, String globalId){
		httpConnection.sendREST("http://211.105.21.17:1234/order", String.valueOf(id), globalId); //외부 API 요청	
		int updateCnt = userMapper.withdraw(id);
		if(updateCnt != 1)flag = false;
		//beforeTransaction Event
		BeforeTransactionEvent beforeTransactionEvent = new BeforeTransactionEvent() {
			@Override
			public void callback() throws RuntimeException {
				// TODO Auto-generated method stub
				canCommit = httpConnection.sendREST("http://211.105.21.17:1111/prepared", Boolean.toString(flag), globalId); //tm에게 commit 여부 전달
					if(canCommit.equals("false")) throw new RuntimeException();				
			}
		};
		AfterTransactionEvent afterTransactionEvent = new AfterTransactionEvent() {
			@Override
			public void callback() {
				log.info("transaction end");
			}
			@Override
			public void completed() {
				log.info("commit end");
			}
		};
		
		applicationEventPublisher.publishEvent(beforeTransactionEvent);
		applicationEventPublisher.publishEvent(afterTransactionEvent);
	}
	
	
}
