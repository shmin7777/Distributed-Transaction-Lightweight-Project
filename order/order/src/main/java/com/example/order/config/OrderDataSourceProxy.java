package com.example.order.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.ttddyy.dsproxy.support.ProxyDataSource;
import net.ttddyy.dsproxy.support.ProxyDataSourceBuilder;

@Configuration
public class OrderDataSourceProxy {

	@Bean
	public DataSource dataSource(DataSourceProperties dataSourceProperties) {
		DataSource dataSource = DataSourceBuilder.create()
		.url(dataSourceProperties.getUrl())
		.username(dataSourceProperties.getUsername())
		.password(dataSourceProperties.getPassword())
		.build();
		
		ProxyDataSource proxyDataSource = ProxyDataSourceBuilder
		.create(dataSource)
		.name("dataSource")
		.logQueryToSysOut()
		.asJson()
		.countQuery()
		.beforeQuery(null)
		.afterQuery((execInfo, queryInfoList)->{
			 System.out.println("Elapsed time : " + execInfo.getElapsedTime() + " ms\n");
		})
		.build();
		
		return proxyDataSource;
	}
	

}
