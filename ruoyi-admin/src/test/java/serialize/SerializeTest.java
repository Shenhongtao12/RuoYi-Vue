package serialize;

import com.alibaba.fastjson2.JSONObject;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

/**
 * @author as2i
 * @date 2023/1/3 10:24
 */
public class SerializeTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(SerializeTest.class);

    private static final ObjectMapper OBJECT_MAPPER;

    public SerializeTest() {
    }

    public static void main(String[] args) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "abcd");
        LOGGER.info(jsonObject.toString());
        String serialize = serialize(jsonObject);
        LOGGER.info(serialize);
    }

    public static <T> String serialize(T t) {
        try {
            return OBJECT_MAPPER.writeValueAsString(t);
        } catch (IOException var2) {
            LOGGER.error("SerializeUtils serialize " + t + " exception: " + var2.toString());
            throw new RuntimeException(var2);
        }
    }

    public static ObjectMapper getMapper() {
        return OBJECT_MAPPER;
    }

    static {
        OBJECT_MAPPER = new ObjectMapper();
        OBJECT_MAPPER.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        OBJECT_MAPPER.configure(JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS.mappedFeature(), true);
        OBJECT_MAPPER.setPropertyNamingStrategy(PropertyNamingStrategy.UPPER_CAMEL_CASE);
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addDeserializer(Timestamp.class, new CustomTimestampDeserializer());
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")));
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern("HH:mm:ss")));
        javaTimeModule.addSerializer(Timestamp.class, new CustomTimestampSerializer());
        OBJECT_MAPPER.registerModule(javaTimeModule);


    }
}

class CustomTimestampSerializer extends JsonSerializer<Timestamp> {
    private static final String TIMESTAMP_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
    private static ThreadLocal<DateFormat> dateFormat_threadLocal = new ThreadLocal<DateFormat>() {
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        }
    };

    public CustomTimestampSerializer() {
        ((DateFormat)dateFormat_threadLocal.get()).setTimeZone(TimeZone.getDefault());
    }

    public void serialize(Timestamp timestamp, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(((DateFormat)dateFormat_threadLocal.get()).format(timestamp));
    }
}

class CustomTimestampDeserializer extends JsonDeserializer<Timestamp> {
    public CustomTimestampDeserializer() {
    }

    public Timestamp deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        return new Timestamp(System.currentTimeMillis());
    }
}
