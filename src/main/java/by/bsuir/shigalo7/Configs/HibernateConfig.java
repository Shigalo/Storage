package by.bsuir.shigalo7.Configs;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class HibernateConfig {

    @Bean
    public DataSource datasource() {
        return DataSourceBuilder.create()
                .driverClassName("com.mysql.jdbc.Driver")
                Эту строку убрать, написал чтоб ошибку показало, файл создания бд \Storage\generate.sql
                .url("jdbc:mysql://localhost:3306/storage?useSSL=false")//Имя БД
                .username("root")//Имя пользователя БД
                .password("root")//Пароль пользователя БД
                .build();
    }
}
