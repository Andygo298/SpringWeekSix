package com.github.andygo298.springWeekSix;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(classes = SpringWeekSixApplication.class)
@Transactional
public abstract class IntegrationTestBase {

}
