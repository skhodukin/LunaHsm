package com.example.luna.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.luna.domain.Response;
import com.example.luna.exceptions.AesException;
import com.example.luna.service.AesLunaService;
import com.example.luna.service.DecryptService;
import com.example.luna.service.EncryptService;

@RestController
public class EncryptDecryptController {

	@Autowired
	EncryptService encryptService;

	@Autowired
	DecryptService decryptService;

	@Autowired
	AesLunaService aesLunaService;

	@GetMapping("/crypto/encrypt/{plainText}")
	public Response encryptString(@PathVariable("plainText") String plainText) {

		return encryptService.encryptString(plainText);
	}

	@GetMapping("/crypto/decrypt")
	public Response decryptString(@RequestParam("encText") String encText) {

		return decryptService.decryptString(encText);
	}

	@GetMapping("/luna/encrypt/{plainText}")
	public Response encryptWithLuna(@PathVariable("plainText") String plainText) throws UnsupportedEncodingException, AesException {
		return aesLunaService.encrypt(plainText);
	}

	@GetMapping("/luna/decrypt")
	public Response decryptWithLuna(@RequestParam("encText") String encText) throws UnsupportedEncodingException, AesException {
		return aesLunaService.decrypt(encText);
	}
}
