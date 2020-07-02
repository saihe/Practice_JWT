package ksaito.practice.jwt.converter;

import java.sql.Timestamp;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply=true)
public class TimestampConverter implements AttributeConverter<Timestamp, String> {
  @Override
  public String convertToDatabaseColumn(Timestamp attribute) {
    return attribute.toString();
  }

  @Override
  public Timestamp convertToEntityAttribute(String dbData) {
    return Timestamp.valueOf(dbData);
  }
}
