package com.example.order.listener;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.example.order.dto.EventDTO;
import com.example.order.listener.AfterTransactionEvent;
import com.example.order.listener.BeforeTransactionEvent;
import com.example.order.listener.OrderEventHandler;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class OrderEventHandler {

	@EventListener
	public void process(EventDTO eventDTO ) {
		log.info("this is listener" + eventDTO.isCommit());
	}
	
	@TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
	public void beforeTransactionProcess(BeforeTransactionEvent beforeTransactionEvent) {
		beforeTransactionEvent.callback();
	}
	
	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	public void afterTransactionProcess(AfterTransactionEvent afterTransactionEvent) {
		afterTransactionEvent.callback();
	}
	
	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMPLETION)
	public void completedTransactionProcess(AfterTransactionEvent afterTransactionEvent) {
		afterTransactionEvent.completed();
	}
}
