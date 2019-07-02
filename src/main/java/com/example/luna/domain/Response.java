package com.example.luna.domain;

public class Response {

	String plainText;
	String encryptedText;

	public Response(String plainText, String encryptedText) {
		this.plainText = plainText;
		this.encryptedText = encryptedText;
	}

	public String getPlainText() {
		return plainText;
	}

	public void setPlainText(String plainText) {
		this.plainText = plainText;
	}

	public String getEncryptedText() {
		return encryptedText;
	}

	public void setEncryptedText(String encryptedText) {
		this.encryptedText = encryptedText;
	}
}
