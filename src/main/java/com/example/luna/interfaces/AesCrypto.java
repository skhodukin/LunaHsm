package com.example.luna.interfaces;

import java.io.Serializable;

import com.example.luna.exceptions.AesException;

public interface AesCrypto extends Serializable {

	byte[] encryptData(byte[] srcData) throws AesException;

	byte[] decryptData(byte[] srcData) throws AesException;

}
