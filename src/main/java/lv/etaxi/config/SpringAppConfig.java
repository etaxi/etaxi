package lv.etaxi.config;

import lv.etaxi.dao.AdminDAO;
import lv.etaxi.dao.CustomerDAO;
import lv.etaxi.dao.OrderDAO;
import lv.etaxi.dao.TaxiDAO;
import lv.etaxi.dao.hibernate.AdminHibernateDAOImpl;
import lv.etaxi.dao.hibernate.CustomerHibernateDAOImpl;
import lv.etaxi.dao.hibernate.OrderHibernateDAOImpl;
import lv.etaxi.dao.hibernate.TaxiHibernateDAOImpl;
import lv.etaxi.dao.jdbc.*;
import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

import java.beans.PropertyVetoException;
import java.util.Properties;

/**
 * Created by D.Lazorkin on 08.04.2016.
 */

@Configuration
@ComponentScan (basePackages = {"lv.etaxi"} )
@EnableTransactionManagement
public class SpringAppConfig {

    private static final String DATABASE_PROPERTIES_FILE = "config.properties";

    @Bean
    public static PropertySourcesPlaceholderConfigurer prodPropertiesPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer p = new PropertySourcesPlaceholderConfigurer();
        Resource[] resourceLocations = new Resource[] {
                new ClassPathResource(DATABASE_PROPERTIES_FILE)
        };
        p.setLocations(resourceLocations);
        return p;
    }

    @Bean
    public Properties hibernateProperties(
            @Value("${hibernate.dialect}") String dialect,
            @Value("${hibernate.show_sql}") boolean showSql,
            @Value("${hibernate.format_sql}") boolean formatSql,
            @Value("${hibernate.hbm2ddl.auto}") String hbm2ddl) {

        Properties properties = new Properties();
        properties.put("hibernate.dialect", dialect);
        properties.put("hibernate.show_sql", showSql);
        properties.put("hibernate.format_sql", formatSql);
        //properties.put("hibernate.hbm2ddl.auto", hbm2ddl);
        return properties;
    }

    @Bean(destroyMethod = "close")
    public DataSource dataSource(
            @Value("${driverClass}") String driver,
            @Value("${dbUrl}") String url,
            @Value("${database.userName}") String user,
            @Value("${password}") String password) throws PropertyVetoException {

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        dataSource.setDefaultAutoCommit(false);

        return dataSource;
    }

    @Bean
    public SessionFactory sessionFactory(DataSource dataSource,
                                         @Value("${hibernate.packagesToScan}") String packagesToScan,
                                         @Qualifier("hibernateProperties") Properties properties) throws Exception {

        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setPackagesToScan(packagesToScan);
        sessionFactoryBean.setHibernateProperties(properties);
        sessionFactoryBean.afterPropertiesSet();
        return sessionFactoryBean.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager(SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }


    @Bean
    @Description("Задаем используемую реализацию интерфейса CustomerDAO")
    CustomerDAO customerDAO() {
        return  (DBConnection.getDatabasePropertyFromFile("db.hibernate").equals("YES")) ?
                new CustomerHibernateDAOImpl() : new CustomerDAOImpl();
    }

    @Bean
    @Description("Задаем используемую реализацию интерфейса OrderDAO")
    OrderDAO orderDAO() {
        return  (DBConnection.getDatabasePropertyFromFile("db.hibernate").equals("YES")) ?
                 new OrderHibernateDAOImpl() : new OrderDAOImpl();
    }

    @Bean
    @Description("Задаем используемую реализацию интерфейса TaxiDAO")
    TaxiDAO taxiDAO() {
        return  (DBConnection.getDatabasePropertyFromFile("db.hibernate").equals("YES")) ?
                 new TaxiHibernateDAOImpl() : new TaxiDAOImpl();
    }

    @Bean
    @Description("Задаем используемую реализацию интерфейса AdminDAO")
    AdminDAO adminDAO() {
        return  (DBConnection.getDatabasePropertyFromFile("db.hibernate").equals("YES")) ?
                 new AdminHibernateDAOImpl() : new AdminDAOImpl();
    }


}
