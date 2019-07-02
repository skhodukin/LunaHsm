package com.example.luna.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.luna.exceptions.AesKeyException;
import com.example.luna.service.AesLunaKeyServiceImpl;

@Configuration
public class ContextConfig {

	@Value("${hsm.slot}")
	private String slot;

	@Value("${hsm.password}")
	private String password;

	@Bean
	public AesLunaKeyServiceImpl aesLunaKeyServiceImpl() throws AesKeyException {
		return new AesLunaKeyServiceImpl(slot, password);
	}

}
