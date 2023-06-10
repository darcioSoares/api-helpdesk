package com.api.helpdesk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.api.helpdesk.services.DBService;

//essas configuraçoes estão apontando para o arquivo q criei applicatio-test.properties para testes
@Configuration
@Profile("dev")
public class DevConfig {
	
	//como esta definido o perfil de dev, ele ira chamar essa classe
	//que o spring ira instanciar e chamar esses metodos
	
	//estou pegando esse valor do arq application-dev.properties pois foi o arq definido em @profile(dev)
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String value;
	
	@Autowired
	private DBService dbService;
	
	@Bean
	public boolean instanciaDB() {
		if(value.equals("create")) {			
			this.dbService.instanciaDB();
		}		
		return false;
	}
	
	
}
