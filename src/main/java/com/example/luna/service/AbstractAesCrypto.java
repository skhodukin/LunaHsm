package com.example.luna.service;

import java.security.*;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;

import org.slf4j.Logger;

import com.example.luna.exceptions.AesException;
import com.example.luna.exceptions.AesKeyException;
import com.example.luna.interfaces.AesCrypto;
import com.example.luna.interfaces.AesKeyService;

public abstract class AbstractAesCrypto implements AesCrypto {

	private static final long serialVersionUID = 5164437858559326208L;

	private AesKeyService aesKeyService;
	private Cipher encCipher;
	private Cipher decrCipher;

	public AbstractAesCrypto(AesKeyService aesKeyService) throws AesException {
		this.aesKeyService = aesKeyService;
		initializeBean();
	}

	protected void initializeBean() throws AesException {
		boolean luna = this instanceof AesLunaCryptoImpl;
		if (!luna) {
			Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
		}
		try {
			encCipher = Cipher.getInstance(getCipherTransformation(), getProvider());

			decrCipher = Cipher.getInstance(getCipherTransformation(), getProvider());
			if (luna) {
				byte[] iv = new byte[] { 0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0a, 0x0b, 0x0c,
					0x0d, 0x0e, 0x0f };
				encCipher.init(Cipher.ENCRYPT_MODE, aesKeyService.getSecretKey(), new IvParameterSpec(iv));
				decrCipher.init(Cipher.DECRYPT_MODE, aesKeyService.getSecretKey(), new IvParameterSpec(iv));
			} else {
				encCipher.init(Cipher.ENCRYPT_MODE, aesKeyService.getSecretKey());
				decrCipher.init(Cipher.DECRYPT_MODE, aesKeyService.getSecretKey());
			}
		} catch (NoSuchAlgorithmException e) {
			getLogger().error("NoSuchAlgorithmException : " + e.getMessage(), e);
			throw new AesException(e);
		} catch (NoSuchProviderException e) {
			getLogger().error("NoSuchProviderException : " + e.getMessage(), e);
			throw new AesException(e);
		} catch (NoSuchPaddingException e) {
			getLogger().error("NoSuchPaddingException : " + e.getMessage(), e);
			throw new AesException(e);
		} catch (InvalidKeyException e) {
			getLogger().error("InvalidKeyException : " + e.getMessage(), e);
			throw new AesException(e);
		} catch (InvalidAlgorithmParameterException e) {
			getLogger().error("InvalidAlgorithmParameterException : " + e.getMessage(), e);
			throw new AesException(e);
		} catch (AesKeyException e) {
			getLogger().error("AesKeyException : " + e.getMessage(), e);
			throw new AesException(e);
		}
	}

	public byte[] encryptData(byte[] srcData) throws AesException {
		try {
			return encCipher.doFinal(srcData);
		} catch (IllegalBlockSizeException e) {
			getLogger().error("IllegalBlockSizeException : " + e.getMessage(), e);
			throw new AesException(e);
		} catch (BadPaddingException e) {
			getLogger().error("BadPaddingException : " + e.getMessage(), e);
			throw new AesException(e);
		}
	}

	public byte[] decryptData(byte[] srcData) throws AesException {
		try {
			return decrCipher.doFinal(srcData);
		} catch (IllegalBlockSizeException e) {
			getLogger().error("IllegalBlockSizeException : " + e.getMessage(), e);
			throw new AesException(e);
		} catch (BadPaddingException e) {
			getLogger().error("BadPaddingException : " + e.getMessage(), e);
			throw new AesException(e);
		}
	}

	protected abstract String getCipherTransformation();

	protected abstract String getProvider();

	protected abstract Logger getLogger();

}