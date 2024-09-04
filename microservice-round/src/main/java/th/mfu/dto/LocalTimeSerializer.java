package th.mfu.dto;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class LocalTimeSerializer extends StdSerializer<LocalTime> {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    public LocalTimeSerializer() {
        this(null);
    }

    public LocalTimeSerializer(Class<LocalTime> clazz) {
        super(clazz);
    }

    @Override
    public void serialize(LocalTime localTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {
        jsonGenerator.writeString(localTime.format(FORMATTER));
    }
}
