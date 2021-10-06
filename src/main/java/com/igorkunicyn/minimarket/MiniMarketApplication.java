package com.igorkunicyn.minimarket;

import com.igorkunicyn.minimarket.configs.MyConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MiniMarketApplication {

	public static void main(String[] args) {
//		MyConfig.getInstance();
		SpringApplication.run(MiniMarketApplication.class, args);
	}

}
