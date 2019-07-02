package com.example.luna.service;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.slf4j.Logger;

import com.example.luna.exceptions.AesKeyException;
import com.example.luna.interfaces.AesKeyService;

public abstract class AbstractAesKeyService implements AesKeyService {

	private static final long serialVersionUID = 6779980429951818786L;

	private int keyLength;
	private SecretKey secretKey = null;

	public SecretKey generateSecretKey() throws AesKeyException {
		try {
			KeyGenerator generator = KeyGenerator.getInstance("AES",
				getProvider());
			generator.init(keyLength);
			return generator.generateKey();
		} catch (NoSuchAlgorithmException e) {
			getLogger()
				.error("NoSuchAlgorithmException : " + e.getMessage(), e);
			throw new AesKeyException(e);
		} catch (NoSuchProviderException e) {
			getLogger().error("NoSuchProviderException : " + e.getMessage(), e);
			throw new AesKeyException(e);
		}
	}

	@Override
	public SecretKey getSecretKey() throws AesKeyException {
		if (secretKey == null) {
			synchronized (this) {
				if (secretKey == null) {
					secretKey = getSecretKeySpec();
				}
			}
		}
		return secretKey;
	}

	protected abstract String getProvider();

	protected abstract SecretKey getSecretKeySpec() throws AesKeyException;

	protected abstract Logger getLogger();

	public int getKeyLength() {
		return keyLength;
	}

	public void setKeyLength(int keyLength) {
		this.keyLength = keyLength;
	}

}