package th.mfu.dto;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class LocalTimeDeserializer extends StdDeserializer<LocalTime> {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    public LocalTimeDeserializer() {
        this(null);
    }

    public LocalTimeDeserializer(Class<?> clazz) {
        super(clazz);
    }

    @Override
    public LocalTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException, JsonProcessingException {
        String time = jsonParser.getText();
        try {
            return LocalTime.parse(time, FORMATTER);
        } catch (DateTimeParseException e) {
            throw new JsonParseException(jsonParser, "Unable to parse time: " + time, e);
        }
    }
}
