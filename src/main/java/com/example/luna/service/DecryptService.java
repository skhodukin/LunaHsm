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
public class DecryptService {

	@Autowired
	CommonKeyGenerator keyGenerator;

	Cipher decrypt;

	public Response decryptString(String encryptedText) {
		byte[] encryptedBytes = Base64.getDecoder().decode(encryptedText);
		String decryptedString = "";
		try {
			decrypt = Cipher.getInstance("AES/CBC/PKCS5Padding");
			decrypt.init(Cipher.DECRYPT_MODE, keyGenerator.getSecretKey(), new IvParameterSpec(new byte[16]));
			byte[] decryptedBytes = decrypt.doFinal(encryptedBytes);
			decryptedString = new String(decryptedBytes);

		} catch (NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		}
		return new Response(decryptedString, encryptedText);
	}
}
