package com.example.luna.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.crypto.SecretKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.luna.exceptions.AesKeyException;

public class AesLunaKeyServiceImpl extends AbstractAesKeyService {

	private static final long serialVersionUID = 6896925467499411927L;
	private static final Logger logger = LoggerFactory.getLogger(AesLunaKeyServiceImpl.class);

	private String slot;
	private String password;

	public AesLunaKeyServiceImpl() {
	}

	public AesLunaKeyServiceImpl(String slot, String password) throws AesKeyException {
		this.slot = slot;
		this.password = password.substring(8, 12) + "-" + password.substring(0, 4) + "-" + password.substring(12, 16)
			+ "-" + password.substring(4, 8);
	}

	protected SecretKey getSecretKeySpec() throws AesKeyException {

		try {
			ByteArrayInputStream is = new ByteArrayInputStream(("slot:" + slot).getBytes());
			KeyStore lunaKeyStore = KeyStore.getInstance("Luna");
			lunaKeyStore.load(is, password.toCharArray());
			return (SecretKey) lunaKeyStore.getKey("AESKey", null);
		} catch (NoSuchAlgorithmException e) {
			logger.error("NoSuchAlgorithmException : " + e.getMessage(), e);
			throw new AesKeyException(e);
		} catch (IOException e) {
			logger.error("IOException : " + e.getMessage(), e);
			throw new AesKeyException(e);
		} catch (CertificateException e) {
			logger.error("CertificateException : " + e.getMessage(), e);
			throw new AesKeyException(e);
		} catch (UnrecoverableKeyException e) {
			logger.error("UnrecoverableKeyException : " + e.getMessage(), e);
			throw new AesKeyException(e);
		} catch (KeyStoreException e) {
			logger.error("KeyStoreException : " + e.getMessage(), e);
			throw new AesKeyException(e);
		}
	}

	@Override
	protected Logger getLogger() {
		return logger;
	}

	@Override
	protected String getProvider() {
		return "LunaProvider";
	}

	public String getSlot() {
		return slot;
	}

	public String getPassword() {
		return password;
	}

}