package th.mfu.dto;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class LocalDateSerializer extends StdSerializer<LocalDate> {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public LocalDateSerializer() {
        this(null);
    }

    public LocalDateSerializer(Class<LocalDate> clazz) {
        super(clazz);
    }

    @Override
    public void serialize(LocalDate localDate, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {
        if (localDate == null) {
            jsonGenerator.writeNull();
        } else {
            jsonGenerator.writeString(localDate.format(FORMATTER));
        }
    }
}
