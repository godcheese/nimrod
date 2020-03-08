package com.gioov.nimrod.common.others;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gioov.nimrod.common.properties.AppProperties;
import com.gioov.nimrod.common.properties.UpdatableMultipartConfigElement;
import com.gioov.nimrod.mail.service.MailService;
import com.gioov.nimrod.system.service.DictionaryService;
import com.gioov.tile.util.ClientUtil;
import com.gioov.tile.util.MBeanServerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.unit.DataSize;
import org.springframework.web.context.WebApplicationContext;

import javax.management.*;
import java.io.IOException;
import java.util.Properties;
import java.util.TimeZone;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
@Component
public class Common {

    private static final Logger LOGGER = LoggerFactory.getLogger(Common.class);

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MailService mailService;

    @Autowired
    private DictionaryService dictionaryService;

    @Autowired
    private AppProperties appProperties;

    @Autowired
    private UpdatableMultipartConfigElement updatableMultipartConfigElement;

    public void initialize() {
        // 首次启动加载数据字典到 ServletContext 内存
        dictionaryService.addDictionaryToServletContext();
        mailService.initialize();
        // 将待发送的邮件重新加入到发送队列
        mailService.retry(false);

        String maxFileSize = (String) dictionaryService.get("FILE", "MAX_FILE_SIZE");
        String maxRequestSize = (String) dictionaryService.get("FILE", "MAX_REQUEST_SIZE");
        updatableMultipartConfigElement.setMaxFileSize(DataSize.parse(maxFileSize).toBytes());
        updatableMultipartConfigElement.setMaxRequestSize(DataSize.parse(maxRequestSize).toBytes());

        String timeZoneId = (String) dictionaryService.get("SYSTEM", "TIME_ZONE_ID");
        TimeZone.setDefault(TimeZone.getTimeZone(timeZoneId));
//        Calendar.getInstance(TimeZone.getTimeZone());
//        DateFormat.getDateTimeInstance().setTimeZone(TimeZone.getTimeZone(timeZoneId));
    }

    public static class Host {
        public static String scheme = null;
        public static String port = null;
        public static String contextPath = null;
        public static String ip = null;
        public static String serverInfo = null;
    }

    public void getHost() {
        getHost(webApplicationContext);
    }

    public static void getHost(WebApplicationContext webApplicationContext) {
        try {
            Host.ip = ClientUtil.getLocalHostLANAddress().getHostAddress();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Host.serverInfo =  webApplicationContext.getServletContext().getServerInfo();
        try {
            Host.scheme = MBeanServerUtil.getScheme();
            Host.port = MBeanServerUtil.getPort();
        } catch (MalformedObjectNameException | AttributeNotFoundException | InstanceNotFoundException | MBeanException | ReflectionException e) {
            e.printStackTrace();
        }
        Host.contextPath = webApplicationContext.getServletContext().getContextPath();
    }

    /**
     * 对象转 JSON
     * @param object Object
     * @return String
     * @throws JsonProcessingException JsonProcessingException
     */
    public String objectToJson(Object object) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(object);
    }

    /**
     * JSON 转对象
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
                        if(slashIndex != 0) {
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

    public Object i18n(String key, Object ...params) {
        ClassPathResource classPathResource = new ClassPathResource("i18n/" + appProperties.getI18n() + ".properties");
        Properties properties = new Properties();
        try {
            properties.load(classPathResource.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (params.length > 0) {
            return String.format((String) properties.get(key), params);
        } else {
            return properties.get(key);
        }
    }

    public Object i18n(String key) {
        return i18n(key, new Object[]{});
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
//
//
//    public abstract class CommonEntityAdapter<T> {
//        /**
//         * 用户名
//         */
//        private String username;
//        /**
//         * 角色
//         */
//        private List<RoleEntity> roles;
//
//        /**
//         * 部门
//         */
//        private List<DepartmentEntity> departments;
//
//        /**
//         * 是否已关联
//         */
//        private Integer isAssociated;
//
//        /**
//         * 是否已授权
//         */
//        private Integer isGranted;
//
//        public String getUsername() {
//            return username;
//        }
//
//        public void setUsername(String username) {
//            this.username = username;
//        }
//
//        public List<RoleEntity> getRoles() {
//            return roles;
//        }
//
//        public void setRoles(List<RoleEntity> roles) {
//            this.roles = roles;
//        }
//
//        public List<DepartmentEntity> getDepartments() {
//            return departments;
//        }
//
//        public void setDepartments(List<DepartmentEntity> departments) {
//            this.departments = departments;
//        }
//
//        public Integer getIsAssociated() {
//            return isAssociated;
//        }
//
//        public void setIsAssociated(Integer isAssociated) {
//            this.isAssociated = isAssociated;
//        }
//
//        public Integer getIsGranted() {
//            return isGranted;
//        }
//
//        public void setIsGranted(Integer isGranted) {
//            this.isGranted = isGranted;
//        }
//    }


}
