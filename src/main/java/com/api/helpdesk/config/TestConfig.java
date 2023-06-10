package com.api.helpdesk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.api.helpdesk.services.DBService;

//essas configuraçoes estão apontando para o arquivo q criei applicatio-test.properties para testes
@Configuration
@Profile("test")
public class TestConfig {
	
	//como esta definido o perfil de teste, ele ira chamar essa classe
	//que o spring ira instanciar e chamar esses metodos
	
	@Autowired
	private DBService dbService;
	
	@Bean
	public void instanciaDB() {
		this.dbService.instanciaDB();
	}
	
	
}
