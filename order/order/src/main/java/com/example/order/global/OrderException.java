package com.example.order.global;

public class OrderException extends Exception {
	public OrderException(String msg) {
		super("TrManagerException !!!!! " + msg);
	}
}
