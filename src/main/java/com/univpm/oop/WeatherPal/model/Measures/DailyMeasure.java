package com.univpm.oop.WeatherPal.model.Measures;

import com.univpm.oop.WeatherPal.exceptions.InvalidFormatterException;
import com.univpm.oop.WeatherPal.model.JsonSerializers.DailyMeasureSerializer;
import com.univpm.oop.WeatherPal.model.tools.EpochConverter;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.*;
import java.time.format.*;

/**
 * Class that represents a measure with a date
 * @param T : the concrete type for the measure value
 */
@JsonSerialize(using = DailyMeasureSerializer.class)
public class DailyMeasure<T> extends Measure<T>{

	protected LocalDate date;

	public DailyMeasure(T value, String unit, LocalDate date) {
		super(value, unit);
		this.date = date;
	}

	public DailyMeasure(T value, LocalDate date) {
		this(value, "", date);
	}

	public DailyMeasure(Measure<T> measure, LocalDate date) {
		super(measure.value, measure.unit);
		this.date = date;
	}

	/**
	 * 
	 * @param value : the value of the measure, which is of the non-primitive type {@code T}
	 * @param unit : a string containing the unit of measure (kg, degrees,  metres, etc.)
	 * @param epochSeconds :  second passed from 01-01-1970 (EPOCH)
	 */
	
	public DailyMeasure(T value, String unit, long epochSeconds) {
		super(value, unit);
		date = EpochConverter.toLocalDateTime(epochSeconds).toLocalDate();
	}
	
	public DailyMeasure(T value, long epochSeconds) {
		this(value, "", epochSeconds);
	}

	/**
	 * 
	 * @param value
	 * 		: the value of the measure, which is of the non-primitive type {@code T}
	 * @param unit
	 * 		: a string containing the unit of measure (kg, degrees,  metres, etc.)
	 * @param stringDate
	 * 		: the date of the measure, following the date format specified in {@code dateFormat} 
	 * @param dateFormat
	 * 		: format of {@code stringDate}. It must follow DateTimeFormatter's pattern conventions 
	 * 		(refer to Oracle's <a href="https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html"
	 * 		>documentation on DateTimeFormatter</a>) 
	 * @throws InvalidFormatterException
	 * 		thrown if {@code dateFormat} isn't a valid pattern
	 * @throws DateTimeParseException
	 * 		thrown if {@code stirngDate} cannot be parsed using {@code dateFormat} 's pattern
	 */
	
	public DailyMeasure(T value, String unit, String stringDate, String dateFormat) throws InvalidFormatterException, DateTimeParseException {
		super(value, unit);
		DateTimeFormatter formatter = buildFormatter(dateFormat);
		date = LocalDate.parse(stringDate, formatter);
	}


	/**
	 * 
	 * @param value
	 * 		: the value of the measure, which is of the non-primitive type {@code T}
	 * @param stringDate
	 * 		: the date of the measure, following the date format specified in {@code dateFormat} 
	 * @param dateFormat
	 * 		: format of {@code stringDate}. It must follow DateTimeFormatter's pattern conventions 
	 * 		(refer to Oracle's <a href="https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html"
	 * 		>documentation on DateTimeFormatter</a>) 
	 * @throws InvalidFormatterException
	 * 		thrown if {@code dateFormat} isn't a valid pattern
	 * @throws DateTimeParseException
	 * 		thrown if {@code stirngDate} cannot be parsed using {@code dateFormat} 's pattern
	 */

	public DailyMeasure(T value, String stringDate, String dateFormat) throws InvalidFormatterException, DateTimeParseException {
		this(value, "", stringDate, dateFormat);
	}

	public LocalDate getDate() {
		return date;
	}

	public Measure<T> toMeasure() {
		return new Measure<T>(value, unit);
	}


	/**
	 * 
	 * @param dateFormat
	 * 		: date formatter's pattern. It must follow DateTimeFormatter's pattern conventions 
	 * 		(refer to Oracle's <a href="https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html"
	 * 		>documentation on DateTimeFormatter</a>)
	 * @throws InvalidFormatterException 
	 * 		thrown if {@code dateFormat} isn't a valid pattern
	 */
	
	protected DateTimeFormatter buildFormatter(String dateFormat) throws InvalidFormatterException {
		DateTimeFormatter formatter;
		try {
			formatter = DateTimeFormatter.ofPattern(dateFormat);
		} catch (IllegalArgumentException e) {
			throw new InvalidFormatterException("Invalid formatter pattern"); //rende piu chiaro il nome dell'eccezione nella documentazione
		}
		return formatter;
	}	
	

	/**
	 * 
	 * @param dateFormat
	 * 		: date formatter's pattern. It must follow DateTimeFormatter's pattern conventions 
	 * 		(refer to Oracle's <a href="https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html"
	 * 		>documentation on DateTimeFormatter</a>)
	 * @return 
	 * 		string representation of {@code DailyMeasure} object with personalized date format
	 * @throws InvalidFormatterException
	 * 		thrown if {@code dateFormat} isn't a valid pattern
	 */
	public String toString(String dateFormat) throws InvalidFormatterException {
		DateTimeFormatter formatter = buildFormatter(dateFormat);
		String toReturn = "value: " + value;
		if(unit != "")
			toReturn += " " + unit;
			toReturn += " --- date: " + date.format(formatter);
			return toReturn;
	}
		
	
	/**
	 * 
	 * @return string representation of {@code DailyMeasure} object with defalt date format dd-MM-yyyy
	 */
	@Override
	 public String toString() {
		String toReturn = "";
		try {
			toReturn = toString("dd-MM-yyyy");	
		} catch (InvalidFormatterException e) {
			//non entrero' mai nel catch mai perche' la stringa passata al toString e' un pattern valido
		}
		return toReturn;
	}
}