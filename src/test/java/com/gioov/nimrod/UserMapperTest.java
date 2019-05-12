package com.gioov.nimrod;

import com.gioov.nimrod.user.entity.UserEntity;
import com.gioov.nimrod.user.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2019-05-09
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserMapperTest.class);

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testListAll() {
        List<UserEntity> userEntityList = userMapper.listAll();
        LOGGER.info("userEntityList={}", userEntityList);
    }

}
