package com.example.luna.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.luna.exceptions.AesException;
import com.example.luna.interfaces.AesKeyService;

public class AesLunaCryptoImpl extends AbstractAesCrypto {

	private static final long serialVersionUID = 7785563323264838632L;
	private static final Logger logger = LoggerFactory
		.getLogger(AesLunaCryptoImpl.class);

	public AesLunaCryptoImpl(AesKeyService aesKeyService) throws AesException {
		super(aesKeyService);
	}

	@Override
	protected void initializeBean() throws AesException {
		try {
			super.initializeBean();
		} catch (Throwable e) {
			logger.warn("Failed to initialize AesLunaCryptoImpl...");
		}
	}

	@Override
	protected String getCipherTransformation() {
		return "AES/CBC/PKCS5Padding";
	}

	@Override
	protected String getProvider() {
		return "LunaProvider";
	}

	@Override
	protected Logger getLogger() {
		return logger;
	}

}