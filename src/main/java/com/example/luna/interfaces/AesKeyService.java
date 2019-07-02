package com.example.luna.interfaces;

import java.io.Serializable;
import javax.crypto.SecretKey;
import com.example.luna.exceptions.AesKeyException;

public interface AesKeyService extends Serializable {

	SecretKey getSecretKey() throws AesKeyException;

}