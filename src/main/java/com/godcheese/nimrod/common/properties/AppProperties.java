package com.godcheese.nimrod.common.properties;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
@Component
@ConfigurationProperties(prefix = "app", ignoreUnknownFields = true, ignoreInvalidFields = true)
public class AppProperties {

    private String name;
    private String version;
    private List<String> systemAdminRole = new ArrayList<>(Collections.singletonList("SYSTEM_ADMIN"));
    private String[] permitUrl;
    private String i18n = "zh_cn";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<String> getSystemAdminRole() {
        return systemAdminRole;
    }

    public void setSystemAdminRole(List<String> systemAdminRole) {
        this.systemAdminRole = systemAdminRole;
    }

    public String[] getPermitUrl() {
        return permitUrl;
    }

    public void setPermitUrl(String[] permitUrl) {
        this.permitUrl = permitUrl;
    }

    public String getI18n() {
        return i18n != null ? i18n : "zh_cn";
    }

    public void setI18n(String i18n) {
        this.i18n = i18n;
    }
}
