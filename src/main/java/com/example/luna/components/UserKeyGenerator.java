package com.example.luna.components;

import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.example.luna.interfaces.CommonKeyGenerator;

@Component
public class UserKeyGenerator implements CommonKeyGenerator {

	@Override
	public SecretKey getSecretKey() {
		SecretKeySpec key = new SecretKeySpec("Let'sCipherThis!".getBytes(), "AES");
		return key;
	}
}