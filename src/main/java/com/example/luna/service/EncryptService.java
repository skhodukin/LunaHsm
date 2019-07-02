package com.example.luna.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import com.example.luna.domain.Response;
import com.example.luna.interfaces.CommonKeyGenerator;

@Service
public class EncryptService {

	@Autowired
	CommonKeyGenerator keyGenerator;

	Cipher encrypt;
	byte[] encryptedBytes;

	public Response encryptString(String plainText) {
		try {
			encrypt = Cipher.getInstance("AES/CBC/PKCS5Padding");
			encrypt.init(Cipher.ENCRYPT_MODE, keyGenerator.getSecretKey(), new IvParameterSpec(new byte[16]));
			encryptedBytes = encrypt.doFinal(plainText.getBytes());
		} catch (NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		}
		return new Response(plainText, Base64.getEncoder().encodeToString(encryptedBytes));
	}
}
