package com.wellsfargo;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import com.wellsfargo.model.User;
import com.wellsfargo.repository.UserRepository;
import com.wellsfargo.service.EmailSenderService;

@SpringBootApplication 
public class Application {

	@Autowired
	private EmailSenderService emailSenderService;
	
	@Autowired
    private UserRepository repository;

    @PostConstruct
    public void initUsers() {
        List<User> users = Stream.of(
                new User(101, "hello", "password", "hello@gmail.com"), 
                new User(102, "user1", "pwd1", "user1@gmail.com"),
                new User(103, "user2", "pwd2", "user2@gmail.com"),
                new User(104, "user3", "pwd3", "user3@gmail.com")
        ).collect(Collectors.toList());
        repository.saveAll(users);
    } 

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void triggerMail() {
		emailSenderService.sendSimpleEmail("sudhaksr570@gmail.com", "this order is successfully placed",
				"place the order");
	}
	 
	 
}
