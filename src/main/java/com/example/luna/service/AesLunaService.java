package com.example.luna.service;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.luna.domain.Response;
import com.example.luna.exceptions.AesException;

@Service
public class AesLunaService {

	@Autowired
	private AesLunaKeyServiceImpl lunaKeyService;

	private AesLunaCryptoImpl aesLunaCrypto;

	public AesLunaService() throws AesException {
		aesLunaCrypto = new AesLunaCryptoImpl(lunaKeyService);
	}

	public Response encrypt(String plainText) throws UnsupportedEncodingException, AesException {
		byte[] inputBytes = plainText.getBytes("UTF8");
		byte[] outputBytes = aesLunaCrypto.encryptData(inputBytes);
		return new Response(plainText, new String(Base64.encodeBase64(outputBytes)));
	}

	public Response decrypt(String encryptedValue) throws UnsupportedEncodingException, AesException {
		byte[] inputBytes = Base64.decodeBase64(encryptedValue.getBytes("UTF-8"));
		byte[] outputBytes = aesLunaCrypto.decryptData(inputBytes);
		return new Response(new String(outputBytes), encryptedValue);
	}
}
