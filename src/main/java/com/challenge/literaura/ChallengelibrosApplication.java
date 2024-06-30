package com.challenge.literaura;

import com.challenge.literaura.principal.Principal;
import com.challenge.literaura.repository.RepositoryAutores;
import com.challenge.literaura.repository.RepositoryLibros;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChallengelibrosApplication implements CommandLineRunner {

	@Autowired
	private RepositoryLibros repository;
	@Autowired
	private RepositoryAutores repositoryAutor;

	public static void main(String[] args) {
		SpringApplication.run(ChallengelibrosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(repository, repositoryAutor);
		principal.iniciarApp();

	}
}
