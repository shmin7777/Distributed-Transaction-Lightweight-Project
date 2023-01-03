package com.example.demo.listener;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.example.demo.dto.EventDTO;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class UserEventHandler{

	@EventListener
	public void process(EventDTO eventDTO) {
		log.info("this is listener" + eventDTO.isCommit());
	}
	
	@TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
	public void beforeTransactionProcess(BeforeTransactionEvent beforeTransactionEvent) throws RuntimeException{
		beforeTransactionEvent.callback();
	}
	
	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	public void afterTransactionProcess(AfterTransactionEvent afterTransactionEvent) throws RuntimeException {
		afterTransactionEvent.callback();
	}
	
	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMPLETION)
	public void completedTransactionProcess(AfterTransactionEvent afterTransactionEvent) {
		afterTransactionEvent.completed();
	}
}
