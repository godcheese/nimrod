package com.gioov.nimrod.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gioov.nimrod.mail.service.MailService;
import com.gioov.nimrod.system.service.DictionaryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
@Component
public class Common {

    private static final Logger LOGGER = LoggerFactory.getLogger(Common.class);

    @Autowired
    private MailService mailService;

    @Autowired
    private DictionaryService dictionaryService;

    public void initialize() {
        // 首次启动加载数据字典到 ServletContext 内存
        dictionaryService.addDictionaryToServletContext();
        mailService.initialize();
    }

    /**
     * 对象转 JSON
     *
     * @param object Object
     * @return String
     * @throws JsonProcessingException JsonProcessingException
     */
    public String objectToJson(Object object) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(object);
    }

    /**
     * JSON 转对象
     *
     * @param json  JSON
     * @param clazz Class
     * @param <T>   T
     * @return T
     * @throws IOException IOException
     */
    public <T> T jsonToObject(String json, Class<T> clazz) throws IOException {
        return new ObjectMapper().readValue(json, clazz);
    }

    public static String trimSlash(String string) {
        if(string != null) {
            int slashIndex = string.indexOf("/");
            if(slashIndex == 0 && string.length()>1) {
                while (true) {
                    string = string.substring(1);
                    if (string.length() >= 1) {
                        slashIndex = string.indexOf("/");
                        if(slashIndex>0 || slashIndex < 0) {
                            return string;
                        }
                    }
                }
            } else {
                return string;
            }
        }
        return null;
    }

    /**
     * 默认区域/语言
     */
//    private static final Locale LOCALE = Locale.CHINA;

    /**
     * 默认时区
     */
//    private static final TimeZone DEFAULT_TIME_ZONE = TimeZone.getTimeZone("GMT+8");

    /**
     * 默认日期格式
     */
//    private static final String DEFAULT_DATE_FORMAT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    //    public TimeZone getSystemTimeZone() {
//        String timeZoneId = (String) dictionaryService.get("SYSTEM", "TIME_ZONE_ID");
//        if (timeZoneId != null) {
//            TimeZone timeZone = TimeZone.getTimeZone(timeZoneId);
//            if (!DEFAULT_TIME_ZONE.hasSameRules(timeZone)) {
//                return timeZone;
//            }
//        }
//        return DEFAULT_TIME_ZONE;
//    }
//

//
//    private ObjectMapper getObjectMapper() {
//        String dateFormatPattern = (String) dictionaryService.get("SYSTEM", "DATE_FORMAT_PATTERN");
//        String language = (String) dictionaryService.get("SYSTEM", "LANGUAGE");
//        String county = (String) dictionaryService.get("SYSTEM", "COUNTY");
//        LOGGER.info("dateFormatPattern1={}", dateFormatPattern);
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.setTimeZone(getSystemTimeZone());
//        objectMapper.setDateFormat(new SimpleDateFormat(dateFormatPattern != null ? dateFormatPattern : DEFAULT_DATE_FORMAT_PATTERN));
//        objectMapper.setLocale((language != null && county != null) ? new Locale(language, county) : LOCALE);
//        return objectMapper;
//    }

}
