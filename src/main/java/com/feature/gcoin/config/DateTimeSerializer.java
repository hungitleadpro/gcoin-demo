package com.feature.gcoin.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.feature.gcoin.common.util.Utils;


import java.io.IOException;
import java.util.Date;

/**
 * @author TienNV
 * @CreatedDate Oct 4, 2017 2:40:54 PM
 */
public class DateTimeSerializer extends JsonSerializer<Date> {

	@Override
	public void serialize(Date value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		String output = Utils.SIMPLE_FOMAT_DATE_TIME.format(value);
		gen.writeString(output);
	}

}
