package com.feature.gcoin.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.feature.gcoin.common.util.Utils;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

/**
 * @author TienNV
 * @CreatedDate Oct 4, 2017 2:44:45 PM
 */
public class DateTimeDeserialize extends JsonDeserializer<Date> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.fasterxml.jackson.databind.JsonDeserializer#deserialize(com.fasterxml
	 * .jackson.core.JsonParser,
	 * com.fasterxml.jackson.databind.DeserializationContext)
	 */
	@Override
	public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		String date = p.getText();
		try {
			return Utils.SIMPLE_FOMAT_DATE_TIME.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

}
