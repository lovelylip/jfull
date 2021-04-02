package vn.silk.me.cucumber;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import vn.silk.me.GatewayApp;

@CucumberContextConfiguration
@SpringBootTest(classes = GatewayApp.class)
@WebAppConfiguration
public class CucumberTestContextConfiguration {}
