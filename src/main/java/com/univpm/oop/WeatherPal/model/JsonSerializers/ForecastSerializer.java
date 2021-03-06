package com.univpm.oop.WeatherPal.model.JsonSerializers;

import com.univpm.oop.WeatherPal.model.Forecast.Forecast;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class ForecastSerializer extends StdSerializer<Forecast> {

	public ForecastSerializer() {
		this(null);
	}

	public ForecastSerializer(Class<Forecast> klazz) {
		super(klazz);
	}

	@Override
	public void serialize(Forecast forecast, JsonGenerator jgen, SerializerProvider provider) throws IOException {
		
		jgen.writeStartObject();
		jgen.writeStringField("date", forecast.getDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
		jgen.writePOJOField("weather", forecast.getWeather());
		jgen.writePOJOField("temperature", forecast.getTemp());
		jgen.writePOJOField("feels like", forecast.getFeelsLike());
		jgen.writePOJOField("humidity", forecast.getHumidity());
		jgen.writePOJOField("wind", forecast.getWind());
		jgen.writePOJOField("pressure", forecast.getPressure());
		jgen.writePOJOField("clouds", forecast.getClouds());
		jgen.writePOJOField("precipitation prob.", forecast.getPop());
		if(forecast.getAirPoll() != null)
			jgen.writePOJOField("air pollution", forecast.getAirPoll());
		jgen.writeEndObject();
	}
	
}
