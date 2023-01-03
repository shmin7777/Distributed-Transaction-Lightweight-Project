package com.example.trserver.service;

import java.util.concurrent.Future;

public interface TrServerService {
	// TransactionID 생성
	public String createTrId();
	
	// 같은 trID가 두개 있는지 확인, 두 개 있고 둘 다 Y면 commit, 아니면 rollback 리턴
	public String checkTrId(String trId, String prepared);
	
//	// 요청 리스트에 add
//	public void addMap(String trId, String prepared);
//	
}
