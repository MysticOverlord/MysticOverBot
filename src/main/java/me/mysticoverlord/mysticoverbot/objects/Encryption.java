package me.mysticoverlord.mysticoverbot.objects;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Encryption {
		SecretKey secret;
    	Cipher encryptcipher;
    	Cipher decryptcipher;
    	IvParameterSpec ivspec;
	    private final Logger logger;

	    public Encryption(final char[] key, final byte[] salt) {
	        logger = LoggerFactory.getLogger(this.getClass());
	    	SecretKeyFactory factory;
			try {
		    	byte[] iv = { 5, 7, 2, 6, 2, 5, 9, 9, 0, 1, 0, 2, 6, 1, 8, 0 };
		    	ivspec = new IvParameterSpec(iv);
				factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
		    	KeySpec spec = new PBEKeySpec(key, salt, 65536, 256);
		    	SecretKey tmp = factory.generateSecret(spec);
		        secret = new SecretKeySpec(tmp.getEncoded(), "AES");
				encryptcipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
				decryptcipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
				encryptcipher.init(Cipher.ENCRYPT_MODE, secret, ivspec);
				decryptcipher.init(Cipher.DECRYPT_MODE, secret, ivspec);
				logger.debug("Encryption Services initialized!");
			} catch (NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }

	    public String encrypt(final String plaintext) {
			try {
				return Base64.getEncoder().encodeToString(encryptcipher.doFinal(plaintext.getBytes("UTF-8")));
			} catch (IllegalBlockSizeException | BadPaddingException | UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				ExceptionHandler.handle(e);
			}
	    	return plaintext;
	    }
	    
	    public String decrypt(final String encryptedtext) {
	    	try {
		    	String plaintext = new String(decryptcipher.doFinal(Base64.getDecoder().decode(encryptedtext)));
		    	return plaintext;
			} catch (IllegalBlockSizeException | BadPaddingException e) {
				// TODO Auto-generated catch block
				ExceptionHandler.handle(e);
			}
	    	return null;
	    }
	}

