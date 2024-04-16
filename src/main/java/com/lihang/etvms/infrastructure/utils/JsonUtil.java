package com.lihang.etvms.infrastructure.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.lihang.etvms.exception.EtvmsSystemException;
import com.lihang.etvms.exception.EtvmsSystemExceptionMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * JSON 工具类
 *
 * @date 2022/12/16
 **/
public class JsonUtil {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    static {
        MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

        // Java 8 data/time handle
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DateTimeUtil.YYYY_MM_DD_HH_MM_SS)));
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DateTimeUtil.YYYY_MM_DD_HH_MM_SS)));

        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern(DateTimeUtil.YYYY_MM_DD)));
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern(DateTimeUtil.YYYY_MM_DD)));

        javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern(DateTimeUtil.HHMMSS)));
        javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern(DateTimeUtil.HHMMSS)));

        MAPPER.registerModule(javaTimeModule);
    }

    private JsonUtil() {

    }

    /**
     * 转换成JSON字符串
     *
     * @param target 目标对象
     * @param <T>    泛型
     * @return JSON字符串
     */
    public static <T> String toJson(T target) {
        if (null == target) {
            return null;
        }
        try {
            return MAPPER.writeValueAsString(target);
        } catch (JsonProcessingException e) {
            LOGGER.error("[JSON error]", e);
            throw EtvmsSystemException.of(EtvmsSystemExceptionMessage.JSON_PARSE_ERROR);
        }
    }

    /**
     * JSON字符串转换为对象
     *
     * @param json  JSON
     * @param clazz 转换目标类
     * @param <T>   泛型
     * @return 对象
     */
    public static <T> T parse(String json, Class<T> clazz) {
        if ("".equals(json) || null == json) {
            return null;
        }

        try {
            return MAPPER.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            LOGGER.error("[JSON parse] - {}", json, e);
            throw EtvmsSystemException.of(EtvmsSystemExceptionMessage.JSON_PARSE_ERROR);
        }
    }

    /**
     * JSON转换为自定义泛型类
     *
     * @param json      JSON
     * @param valueType 类型参考
     * @param <T>       泛型
     * @return 自定义泛型类
     */
    public static <T> T parse(String json, TypeReference<T> valueType) {
        if ("".equals(json) || null == json) {
            return null;
        }

        try {
            return MAPPER.readValue(json, valueType);
        } catch (JsonProcessingException e) {
            LOGGER.error("[JSON parse type reference] - {}", json, e);
            throw EtvmsSystemException.of(EtvmsSystemExceptionMessage.JSON_PARSE_ERROR);
        }
    }
}
