package com.example.order.listener;

import com.example.order.listener.AbstractTransactionEvent;

public interface AfterTransactionEvent  extends AbstractTransactionEvent{
	void completed();

}
