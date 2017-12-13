package org.tm.pro.zk;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@ImportResource(locations = "classpath:provider.xml")
@SpringBootApplication
public class StartApp {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(StartApp.class, args);
		System.in.read();
	}
}