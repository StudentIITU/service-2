package com.damir.notificationservice;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@Import(com.damir.notificationservice.TestcontainersConfiguration.class)
@AutoConfigureMockMvc
public abstract class AbstractIT {}
