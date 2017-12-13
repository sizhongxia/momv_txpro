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
		byte[] input = new byte[20];
		while (true) {
			System.out.println("input `exit` to quit!");
			System.in.read(input);
			if (new String(input).trim().equals("exit")) {
				break;
			}
		}
	}
}