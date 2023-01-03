package com.example.demo.listener;

public interface AbstractTransactionEvent {
	void callback() throws RuntimeException;
}
