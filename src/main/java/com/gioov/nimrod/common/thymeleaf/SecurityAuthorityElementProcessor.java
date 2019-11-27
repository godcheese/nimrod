package com.gioov.nimrod.common.thymeleaf;

import com.gioov.nimrod.common.security.SimpleUser;
import com.gioov.nimrod.common.security.SimpleUserDetailsServiceImpl;
import com.gioov.tile.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.context.WebEngineContext;
import org.thymeleaf.model.IAttribute;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractElementTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.standard.StandardDialect;
import org.thymeleaf.templatemode.TemplateMode;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2019-09-23
 */
public class SecurityAuthorityElementProcessor extends AbstractElementTagProcessor {
    private static final String ELEMENT_NAME = "security";
    private static final String AUTHORITY_ATTRIBUTE_NAME = "authority";
    private static final String ROLE_ATTRIBUTE_NAME = "role";
    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityAuthorityElementProcessor.class);

    public SecurityAuthorityElementProcessor(String dialectPrefix) {
        super(TemplateMode.HTML, dialectPrefix, ELEMENT_NAME, true, null, false, StandardDialect.PROCESSOR_PRECEDENCE);
    }

    @Override
    protected void doProcess(ITemplateContext iTemplateContext, IProcessableElementTag iProcessableElementTag, IElementTagStructureHandler iElementTagStructureHandler) {
        WebEngineContext context2 = (WebEngineContext) iTemplateContext;
        HttpServletRequest request = context2.getRequest();
        SimpleUser simpleUser = SimpleUserDetailsServiceImpl.getCurrentSimpleUser(request);
        boolean hasAuthority = false;
        if(simpleUser != null) {
            List<String> authorityList = new ArrayList<>(1);
            IAttribute authority = iProcessableElementTag.getAttribute(AUTHORITY_ATTRIBUTE_NAME);
            if (authority != null) {
                String value = authority.getValue().toUpperCase();
                if (value.contains(",")) {
                    authorityList = StringUtil.splitAsList(value, ",");
                } else {
                    authorityList.add(value);
                }
            }
            List<String> roleList = new ArrayList<>(1);
            IAttribute role = iProcessableElementTag.getAttribute(ROLE_ATTRIBUTE_NAME);
            if (role != null) {
                String value = role.getValue().toUpperCase();
                if (value.contains(",")) {
                    roleList = StringUtil.splitAsList(value, ",");
                } else {
                    roleList.add(value);
                }
            }
            for (String a : authorityList) {
                if (SimpleUserDetailsServiceImpl.isExistsAuthority(simpleUser.getAuthorities(), a)) {
                    hasAuthority = true;
                }
            }
            for (String a : roleList) {
                a = SimpleUserDetailsServiceImpl.ROLE_PREFIX + a;
                if (SimpleUserDetailsServiceImpl.isExistsAuthority(simpleUser.getAuthorities(), a)) {
                    hasAuthority = true;
                }
            }
        }
        if(hasAuthority) {
            iElementTagStructureHandler.removeTags();
        } else {
            iElementTagStructureHandler.removeElement();
        }
    }
}
