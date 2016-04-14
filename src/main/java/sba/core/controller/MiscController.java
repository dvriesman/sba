package sba.core.controller;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/misc")
public class MiscController {

	
	@Autowired
    	StandardPBEStringEncryptor encryptor;
	
	@RequestMapping(value = "/encrypt/{word}", method = RequestMethod.GET, produces = "application/json")
	public String cryptoWord(@PathVariable("word") String word) throws Exception {
		return encryptor.encrypt(word);
	}
	
	@RequestMapping(value = "/wardecript/{word}", method = RequestMethod.GET, produces = "application/json")
	public String wardecript(@PathVariable("word") String word) throws Exception {
		return encryptor.decrypt(word);
	}	
	


}
