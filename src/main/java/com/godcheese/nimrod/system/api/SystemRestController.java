package com.godcheese.nimrod.system.api;

import com.godcheese.nimrod.common.operationlog.OperationLog;
import com.godcheese.nimrod.common.operationlog.OperationLogType;
import com.godcheese.nimrod.common.others.FailureEntity;
import com.godcheese.nimrod.system.System;
import com.godcheese.nimrod.system.service.DictionaryService;
import com.godcheese.tile.util.ColorUtil;
import com.godcheese.tile.util.ImageUtil;
import com.godcheese.tile.util.RandomUtil;
import com.godcheese.tile.web.exception.BaseResponseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import static com.godcheese.nimrod.common.security.SimpleUserDetailsServiceImpl.SYSTEM_ADMIN;


/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
@RestController
@RequestMapping(value = System.Api.SYSTEM, produces = MediaType.APPLICATION_JSON_VALUE)
public class SystemRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SystemRestController.class);

    private static final String SYSTEM = "/API/SYSTEM";

    public static final String VERIFY_CODE_NAME = "verifyCode";

    @Autowired
    private DictionaryService dictionaryService;

    @Autowired
    private FailureEntity failureEntity;

    /**
     * 获取验证码
     *
     * @param httpServletResponse HttpServletResponse
     * @param httpServletRequest  HttpServletRequest
     * @throws BaseResponseException BaseResponseException
     */
    @OperationLog(value = "获取验证码", type = OperationLogType.API)
    @GetMapping(value = "/verify_code")
    public void verifyCode(HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) throws BaseResponseException {
        long expiration = Long.parseLong((String) dictionaryService.get("VERIFY_CODE", "EXPIRATION"));
        boolean yawp = Boolean.parseBoolean((String) dictionaryService.get("VERIFY_CODE", "YAWP"));
        int stringLength = Integer.parseInt((String) dictionaryService.get("VERIFY_CODE", "STRING_LENGTH"));
        int interLine = Integer.parseInt((String) dictionaryService.get("VERIFY_CODE", "INTER_LINE"));
        String hexBackgroundColor = String.valueOf(dictionaryService.get("VERIFY_CODE", "HEX_BACKGROUND_COLOR"));
        String fontColor = String.valueOf(dictionaryService.get("VERIFY_CODE", "FONT_COLOR"));
        String fontPath = String.valueOf(dictionaryService.get("VERIFY_CODE", "FONT_PATH"));
        stringLength = (stringLength >= 3 && stringLength <= 8) ? stringLength : 4;
        interLine = (interLine >= 1 && interLine <= 8) ? interLine : 0;
        expiration = (expiration >= 20) ? expiration : 60;
        hexBackgroundColor = hexBackgroundColor.length() == 7 ? hexBackgroundColor : "#0064c8";
        fontColor = fontColor.length() == 7 ? fontColor : "#ffffff";
        ImageUtil.VerifyCodeImage verifyCodeImage;
        try {

            InputStream fontInputStream = null;
            if (fontPath.startsWith(ResourceLoader.CLASSPATH_URL_PREFIX)) {
                fontPath = fontPath.substring(ResourceLoader.CLASSPATH_URL_PREFIX.length());
                fontInputStream = new ClassPathResource(fontPath).getInputStream();
            } else {
                fontInputStream = this.getClass().getClassLoader().getResourceAsStream(fontPath);
            }

            if (fontInputStream == null) {
                throw new BaseResponseException(failureEntity.i18n("system.verify_code_create_fail_font_not_exists", fontPath));
            }

            verifyCodeImage = ImageUtil.createVerifyCodeImage(114, 40, ColorUtil.getRGBColorByHexString(hexBackgroundColor), RandomUtil.randomString(stringLength, RandomUtil.NUMBER_LETTER), ColorUtil.getRGBColorByHexString(fontColor), fontInputStream, yawp, interLine, expiration);

            httpServletResponse.addHeader("Pragma", "no-cache");
            httpServletResponse.addHeader("Cache-Control", "no-cache");
            httpServletResponse.addHeader("Expires", "0");
            // 生成验证码，写入用户session
            httpServletRequest.getSession().setAttribute(VERIFY_CODE_NAME, verifyCodeImage);
            // 输出验证码给客户端
            httpServletResponse.setContentType(MediaType.IMAGE_PNG_VALUE);
            ImageIO.write(verifyCodeImage.getBufferedImage(), "png", httpServletResponse.getOutputStream());
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            throw new BaseResponseException(failureEntity.i18n("system.verify_code_create_fail"));
        }
    }

    /**
     * 获取系统信息
     *
     * @param httpServletResponse HttpServletResponse
     * @param httpServletRequest  HttpServletRequest
     * @return ResponseEntity<Map < String, String>>
     */
    @OperationLog(value = "获取系统信息", type = OperationLogType.API)
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + SYSTEM + "/SYSTEM_INFO')")
    @GetMapping(value = "/system_info")
    public ResponseEntity<Map<String, String>> systemInfo(HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) throws BaseResponseException {
        Map<String, String> map = new HashMap<>(1);
        map.put("osName", java.lang.System.getProperty("os.name"));
        map.put("osVersion", java.lang.System.getProperty("os.version"));
        map.put("osArch", java.lang.System.getProperty("os.arch"));
        map.put("javaHome", java.lang.System.getProperty("java.home"));
        map.put("javaVersion", java.lang.System.getProperty("java.version"));

        /**
         *  "user.timezone": "Asia/Shanghai",
         *     "os.name": "Mac OS X",
         *      "os.version": "10.14.5",
         *      "os.arch": "x86_64",
         *     "java.home": "/Library/Java/JavaVirtualMachines/jdk1.8.0_192.jdk/Contents/Home/jre",
         *           "java.version": "1.8.0_192",
         */
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
