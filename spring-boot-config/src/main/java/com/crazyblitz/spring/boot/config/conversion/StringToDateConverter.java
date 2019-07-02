package com.crazyblitz.spring.boot.config.conversion;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * string to date converter
 *
 * @author liuenyuan
 * @see Converter
 * @see org.springframework.format.Formatter
 * @see org.springframework.core.convert.converter.ConverterRegistry
 * @see org.springframework.format.FormatterRegistrar
 * @see org.springframework.format.FormatterRegistry#addConverter(Converter)
 **/
@Slf4j
public class StringToDateConverter implements Converter<String, Date> {

    private final static String FORMATTER_PATTERN = "yyyy/MM/dd HH:mm:ss";

    /**
     * date format thread local
     **/
    private final ThreadLocal<SimpleDateFormat> dateFormatThreadLocal = ThreadLocal.withInitial(() ->
            new SimpleDateFormat(FORMATTER_PATTERN));

    @Override
    public Date convert(String source) {
        try {
            SimpleDateFormat dateFormat = dateFormatThreadLocal.get();
            dateFormat.setLenient(false);
            Date convert = dateFormat.parse(source);
            log.info("convert source {} to date {} success", source, convert);
            return convert;
        } catch (ParseException ex) {
            if (log.isWarnEnabled()) {
                log.warn("parse source {} to date exception message: {}.", source,
                        ex.getMessage());
            }
            throw new IllegalArgumentException("can not convert source [" + source + "] to date.");
        }
    }
}
