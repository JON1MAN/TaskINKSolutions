package com.task.TaskINKSolutions;

import com.task.TaskINKSolutions.Entities.City;
import com.task.TaskINKSolutions.Entities.News;
import com.task.TaskINKSolutions.Entities.State;
import com.task.TaskINKSolutions.Repositories.CityRepository;
import com.task.TaskINKSolutions.Services.CityService;
import com.task.TaskINKSolutions.Services.GptFilterService;
import com.task.TaskINKSolutions.Services.NewsService;
import com.task.TaskINKSolutions.Services.StateService;
import jakarta.servlet.MultipartConfigElement;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Arrays;

@SpringBootApplication
public class TaskInkSolutionsApplication {
	private final MultipartConfigElement multipartConfigElement;
    @Autowired
    private StateService stateService;
    @Autowired
    private CityService cityService;

	public TaskInkSolutionsApplication(MultipartConfigElement multipartConfigElement) {
		this.multipartConfigElement = multipartConfigElement;
	}

//	@Autowired
//	private CityService cityService;
//	@Autowired
//	private StateService stateService;
	@Autowired
	private GptFilterService gptFilterService;
	@Autowired
	private NewsService newsService;

	public static void main(String[] args) {
		SpringApplication.run(TaskInkSolutionsApplication.class, args);
	}

	/*@EventListener(ApplicationReadyEvent.class)
	public void parseDataSetOfNews() {

		String file = "src/main/resources/static/usnews2.json";
		try{
			String jasonContent = "src/main/resources/static/usnews.json";
			String fileContent = new String(Files.readAllBytes(Paths.get(jasonContent)));

			JSONObject jsonObject = new JSONObject(fileContent);
			//////////////////////
			System.out.println(jsonObject);

			JSONArray jsonArray = jsonObject.getJSONArray("articles");

			for(int i = 96; i < jsonArray.length(); i++){
				//////////////////////
				System.out.println("ARTICLE NUMBER : " + i);

				JSONObject article = jsonArray.getJSONObject(i);
				String author = article.optString("author", "author");
				String title = article.optString("title", "Breaking News");
				String description = article.optString("description", "Breaking news via link!");
				String url = article.optString("url", "link");
				String date = article.optString("date", LocalDate.now().toString());
				String content = article.optString("content", " ");

				String inputText = String.format
						(
								"%s\n%s\n%s\n%s\n%s\n",
								author,
								title,
								description,
								content,
								url
						);
				//////////////////////
				System.out.println(inputText);

				State state = stateService.findStateByName("GLOBAL");
				System.out.println(state.getName());

				JSONObject gptResponse = new JSONObject(
						gptFilterService.textAnalysis(inputText));
				//////////////////////
				System.out.println(gptResponse);

				String contentOfResponse = gptResponse
						.getJSONObject("message")
						.getString("content");
				//////////////////////
				System.out.println(contentOfResponse);
				var x = contentOfResponse.split(", ");
				String[] fields =
						{author, title, description, url, date,
								contentOfResponse.split(",")[0].trim(),
								contentOfResponse.split(",")[1].trim(),
								contentOfResponse.split(",")[2].trim()
						};
				//////////////////////
				Arrays.stream(fields)
						.forEach(System.out::println);
				News news = newsService.createNewsFromGPTResponse(fields);


			}
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}*/
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
