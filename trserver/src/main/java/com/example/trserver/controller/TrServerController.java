package com.example.trserver.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncConfigurationSelector;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.trserver.service.TrServerService;

@RestController
@EnableAsync
public class TrServerController {
	@Autowired
	private TrServerService trServerService;
	
	// trId 생성
	@GetMapping("/trId")
	public JSONObject createTrId() {
		String trId = trServerService.createTrId();
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("trId", trId);
		return jsonObject;
	}
	
	// 요청 들어온 transactionId 체크 후 두개면 응답
	@Async
	@PostMapping("/prepared")
	public String prepared(@RequestHeader String globalId, @RequestBody String prepared) {
		String result = trServerService.checkTrId(globalId, prepared);
		
		return result;
	}
}
