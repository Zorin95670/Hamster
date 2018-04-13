package com.vmp.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;

public class CryptoService {

	private static CryptoService instance = new CryptoService();
	private Cipher cipher;
	private PrivateKey key;

	private CryptoService() {
		try {
			this.cipher = Cipher.getInstance("RSA");
			this.initPrivateKey();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void initPrivateKey() {
		String keyFileName = Configuration.get("security.rsa.private.key.location");
		if (keyFileName == null) {
			keyFileName = "conf/rsa.key";
		}
		File keyFile = new File(keyFileName);
		if (!keyFile.exists()) {
			generateKey(keyFileName);
		} else {
			try {
				byte[] keyBytes = Files.readAllBytes(keyFile.toPath());
				PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
				KeyFactory kf = KeyFactory.getInstance("RSA");
				key = kf.generatePrivate(spec);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void generateKey(String keyFileName) {
		KeyPairGenerator keyGen;
		try {
			keyGen = KeyPairGenerator.getInstance("RSA");
			keyGen.initialize(4096);
			KeyPair pair = keyGen.generateKeyPair();
			key = pair.getPrivate();

			File f = new File(keyFileName);

			FileOutputStream fos = new FileOutputStream(f);
			fos.write(key.getEncoded());
			fos.flush();
			fos.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String encrypt(String text) {
		try {
			instance.cipher.init(Cipher.ENCRYPT_MODE, instance.key);
		} catch (InvalidKeyException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			return new String(Base64.getDecoder().decode(instance.cipher.doFinal(text.getBytes("UTF-8"))));
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
