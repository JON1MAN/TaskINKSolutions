package com.task.TaskINKSolutions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class TaskInkSolutionsApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskInkSolutionsApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void parseDataSetOfCities() {
		
		System.out.println("hello world, I have just started up");
	}

}
