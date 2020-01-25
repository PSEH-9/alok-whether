package com.sapient.whether.controller;

import com.sapient.whether.integration.service.Weather;
import com.sapient.whether.integration.service.WeatherEntry;
import com.sapient.whether.integration.service.WeatherForecast;
import com.sapient.whether.integration.service.WeatherService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/weather")
public class WeatherApiController {

	private final WeatherService weatherService;

	public WeatherApiController(WeatherService weatherService) {
		this.weatherService = weatherService;
	}

	@RequestMapping("/now/{country}/{city}")
	public Weather getWeather(@PathVariable String country,
							  @PathVariable String city) {
		return this.weatherService.getWeather(country, city);
	}

	@RequestMapping("/weekly/{country}/{city}")
	public WeatherForecast getWeatherForecast(@PathVariable String country,
											  @PathVariable String city) {
		WeatherForecast forecast = this.weatherService.getWeatherForecast(country, city);

		List<WeatherEntry> newEntries = new ArrayList<>();
		List<WeatherEntry> entries = forecast.getEntries();
		Date todayDate = new Date();
		for (WeatherEntry weatherEntry : entries) {
			Instant instant = weatherEntry.getTimestamp();
		}
		return forecast;
	}

}
