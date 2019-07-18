package top.aceofspades.ssm.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@ComponentScan(basePackages = {"top.aceofspades.ssm.service"})
@Import({MyBatisConfig.class})
@EnableAspectJAutoProxy
@EnableTransactionManagement
@Configuration
public class RootConfig {
}
