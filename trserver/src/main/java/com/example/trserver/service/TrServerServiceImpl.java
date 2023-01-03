package com.example.trserver.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class TrServerServiceImpl implements TrServerService {
	@Autowired
	private Map<String, Integer> trCount;
	@Autowired
	private Map<String, Boolean> trInfo;
	// TransactionID 생성
	@Override
	public String createTrId() {
		String trId = UUID.randomUUID().toString();

		return trId;
	}

	@Override
	public String checkTrId(String trId, String prepared) {
		System.out.println("TM에서 받은 trID : " + trId);
		if (trCount.get(trId) == null) {
			trCount.put(trId, 1);
			trInfo.put(trId, true);
		} else {
			trCount.put(trId, trCount.get(trId) + 1);
		}

		if (prepared.equals("false")) {
			trInfo.put(trId, false);
		}

		while (trCount.get(trId) < 2) {
			System.out.println("while문");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		return "" + false;
	}


}
