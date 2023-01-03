package com.example.trserver.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TrServerConfig {
	@Bean
	public List<Map<String, String>> trIdMap() {
		return new ArrayList<Map<String, String>>();
	}
	
	@Bean
	public Map<String, Integer> trCount() {
		return new HashMap<String, Integer>();
	}
	
	@Bean
	public Map<String, Boolean> trInfo() {
		return new HashMap<String, Boolean>();
	}
}