package com.inmetrics.DAO;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.util.StringUtils;

import com.inmetrics.domain.Sites;
import com.inmetrics.repo.CustomRepository;
import com.inmetrics.repo.SiteRepository;

public class MongoDAO implements CommandLineRunner {

	@Autowired
	SiteRepository repository;

	@Autowired
	CustomRepository crepo;

	// Estanciando o objeto WebDriver
	protected static WebDriver driver;
	// Criando uma lista de do objeto Sites
	List<Sites> sites;

	private static Logger logger = Logger.getLogger(MongoDAO.class);

	public void run(String... args) throws Exception {
		BasicConfigurator.configure();
		listAll();
		// findFirst();
		// deleteAll();
	}

	/**
	 * Método que deleta todo o banco
	 */
	public void deleteAll() {
		logger.info("Deleting all records..");
		repository.deleteAll();
	}

	/**
	 * Metodo usado para listar a fila do banco MongoDB na nuvem e salvar os print
	 * na pasta
	 * 
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void listAll() throws InterruptedException, IOException {

		sites = new ArrayList<>();

		logger.info("Listing a MongoDB Queue");

		repository.findAll().forEach(u -> sites.add(u));

		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");
		driver = new ChromeDriver();

		String nomeDoArquivo = "printScreen_site_";
		int c = 0;
		String url = "http://";
		for (Sites site : sites) {

			if (StringUtils.startsWithIgnoreCase(site.getAddress(), "www")) {

				logger.info(url + site.getAddress());
				driver.get(url + site.getAddress());

			} else {
				logger.info(site.getAddress());
				driver.get(site.getAddress());

			}

			// File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

			/**
			 * captura as telas dos sites e armazena na pasta pritaSites
			 */
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile,
					new File("C:\\Users\\Lucas Borges\\Pictures\\pritaSites\\" + nomeDoArquivo + c++));
			Thread.sleep(3000);
		}
		// Fechamento do escopo do WebDriver
		driver.close();

	}

	/**
	 * Método que deleta arquivos do banco a partir do id
	 * 
	 * @param id
	 */
	public void deleteById(String id) {
		logger.info("Deleting record");
		repository.deleteById(id);
	}
}
