package com.task.TaskINKSolutions;

import com.task.TaskINKSolutions.Entities.City;
import com.task.TaskINKSolutions.Entities.State;
import com.task.TaskINKSolutions.Repositories.CityRepository;
import com.task.TaskINKSolutions.Services.CityService;
import com.task.TaskINKSolutions.Services.StateService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.io.*;

@SpringBootApplication
public class TaskInkSolutionsApplication {

//	@Autowired
//	private CityService cityService;
//	@Autowired
//	private StateService stateService;

	public static void main(String[] args) {
		SpringApplication.run(TaskInkSolutionsApplication.class, args);
	}

//	@EventListener(ApplicationReadyEvent.class)
//	public void parseDataSetOfCities() {
//
//		File file = new File("src/main/resources/static/uscities.csv");
//		try(BufferedReader bufferedReader = new BufferedReader(
//				new FileReader(file))
//		){
//			String[] headers = {"city","city_ascii","state_id","state_name","county_fips","county_name","lat","lng","population","density","source","military","incorporated","timezone","ranking","zips","id"};
//			CSVFormat csvFormat = CSVFormat.DEFAULT
//					.builder()
//					.setHeader(headers)
//					.setSkipHeaderRecord(true)
//					.build();
//			Iterable<CSVRecord> records = csvFormat.parse(bufferedReader);
//			for(CSVRecord record : records){
//				String cityName = record.get("city").toUpperCase();
//				String stateName = record.get("state_name").toUpperCase();
//
//				State state = new State();
//				state.setName(stateName);
//
//				City city = new City();
//				city.setName(cityName);
//
//				state = stateService.createState(state);
//
//				city.setState(state);
//				cityService.createCity(city);
//			}
//		} catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        System.out.println("hello world, I have just started up");
//	}

}
