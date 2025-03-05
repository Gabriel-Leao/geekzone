package com.geekzone.store;

import com.geekzone.store.utils.EnvUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GeekZoneStoreApplication {

	public static void main(String[] args) {
		System.setProperty("DB_HOST", EnvUtil.getEnv("DB_HOST"));
		System.setProperty("DB_NAME", EnvUtil.getEnv("DB_NAME"));
		System.setProperty("DB_USER", EnvUtil.getEnv("DB_USER"));
		System.setProperty("DB_PASSWORD", EnvUtil.getEnv("DB_PASSWORD"));

		SpringApplication.run(GeekZoneStoreApplication.class, args);
	}

}
