package nl.hu.cisq1.lingo.trainer.data;

import nl.hu.cisq1.lingo.trainer.domain.enums.Mark;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Converter
public class MarksConverter implements AttributeConverter<List<Mark>, String> {
    @Override
    public String convertToDatabaseColumn(List<Mark> marks) {
        String result = "";

        for (Mark mark : marks){
            result += mark.toString() + ",";
        }

        return result;
    }

    @Override
    public List<Mark> convertToEntityAttribute(String dbData) {
        if (dbData.isBlank()) {
            return Collections.emptyList();
        }

        String[] markData = dbData.split(",");
        List<Mark> marks = new ArrayList<>();

        for (String mark : markData) {
            marks.add(Mark.valueOf(mark));
        }

        return marks;
    }
}
