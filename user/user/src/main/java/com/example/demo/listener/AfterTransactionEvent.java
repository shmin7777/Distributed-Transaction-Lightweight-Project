package com.example.demo.listener;

public interface AfterTransactionEvent extends AbstractTransactionEvent{
	void completed();
}
