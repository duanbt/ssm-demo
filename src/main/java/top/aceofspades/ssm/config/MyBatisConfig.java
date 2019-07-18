package top.aceofspades.ssm.config;

import com.github.pagehelper.PageInterceptor;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.Configuration;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;


@MapperScan(basePackages = "top.aceofspades.ssm.mapper")
public class MyBatisConfig {

    @Bean
    public DataSource dateSource() {
        HikariConfig config = new HikariConfig("/hikari.properties");
        return new HikariDataSource(config);
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean() {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dateSource());
        sqlSessionFactoryBean.setTypeAliasesPackage("top.aceofspades.ssm.domain");
        PageInterceptor pageInterceptor = new PageInterceptor();
        Properties pageHelperProps = new Properties();
        pageInterceptor.setProperties(pageHelperProps);
        sqlSessionFactoryBean.setPlugins(pageInterceptor);
        Configuration configuration = new Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        sqlSessionFactoryBean.setConfiguration(configuration);
        return sqlSessionFactoryBean;
    }

    @Bean
    public DataSourceTransactionManager txManager() {
        return new DataSourceTransactionManager(dateSource());
    }

}
