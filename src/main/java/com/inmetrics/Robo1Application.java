package com.inmetrics;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.inmetrics.domain.Sites;
import com.inmetrics.repo.CustomRepository;
import com.inmetrics.repo.SiteRepository;

@SpringBootApplication
public class Robo1Application implements CommandLineRunner {
	@Autowired
	SiteRepository repository;

	@Autowired
	CustomRepository crepo;

	protected static WebDriver driver;

	List<Sites> sites;

	public static void main(String[] args) {
		SpringApplication.run(Robo1Application.class, args);
	}

	public void run(String... args) throws Exception {

		listAll();

		// findFirst();
		// deleteAll();
	}

	public void deleteAll() {
		System.out.println("Deleting all records..");
		repository.deleteAll();
	}

	public void listAll() throws InterruptedException, IOException {

		sites = new ArrayList<>();

		System.out.println("Listando a fila do mongo");

		repository.findAll().forEach(u -> sites.add(u));

		System.setProperty("webdriver.chrome.driver", "/home/lucas/Documentos/chromedriver/chromedriver");

		driver = new ChromeDriver();

		String nomeDoArquivo = "printScreen_site_";
		int c = 0;

		for (Sites site : sites) {

			System.out.println(site.getAddress());
			driver.get(site.getAddress());
			

//			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File("/home/lucas/Downloads/printaSites/printaSites" 
			+ nomeDoArquivo + c++));
			
		}

		driver.close();
		

	}

	public void deleteById(String id) {
		System.out.println("Deleting record");
		repository.deleteById(id);
	}

}
