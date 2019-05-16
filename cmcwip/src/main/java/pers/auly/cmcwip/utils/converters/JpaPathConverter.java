package pers.auly.cmcwip.utils.converters;

import java.nio.file.Path;
import java.nio.file.Paths;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class JpaPathConverter implements AttributeConverter<Path, String> {
    
    @Override
    public String convertToDatabaseColumn(Path attribute) {
        return attribute.toString();
    }
    
    @Override
    public Path convertToEntityAttribute(String dbData) {
        return Paths.get(dbData);
    }
}
