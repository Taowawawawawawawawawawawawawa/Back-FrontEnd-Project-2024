package th.mfu.dto;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

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
        return LocalTime.parse(jsonParser.getText(), FORMATTER);
    }
}
