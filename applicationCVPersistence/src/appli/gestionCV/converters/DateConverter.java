package appli.gestionCV.converters;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.AttributeConverter;

import utils.LogUtils;

public class DateConverter implements AttributeConverter<LocalDate, Date> {

	@Override
	public Date convertToDatabaseColumn(LocalDate localDate) {
		LogUtils.error("convertToDatabaseColumn");
		return (localDate == null) ? null : Date.valueOf(localDate);
	}

	@Override
	public LocalDate convertToEntityAttribute(Date sqlDate) {
		LogUtils.error("convertToEntityAttribute");
		return (sqlDate == null) ? null : sqlDate.toLocalDate();
	}

	
}
