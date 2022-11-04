package org.example.configs;

import jakarta.persistence.Entity;
import javassist.tools.reflect.Reflection;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.reflections.Reflections;

import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

/**
 * @author "Berdimurodov Shoxrux"
 * @since 05/11/22 01:11 (Saturday)
 * quiz-app-console/IntelliJ IDEA
 */
public class HibernateConfigurer {

    private static final SessionFactory sessionFactory =setUp();

    private static SessionFactory setUp() {

        StandardServiceRegistry registry = null;

        if (Objects.isNull(sessionFactory)){
            try {
            StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();
            Properties properties = new Properties();
            properties.load(new FileReader("src/main/resources/datasource.properties"));
            registryBuilder.applySettings(properties);
            registry = registryBuilder.build();
            MetadataSources metadataSources = new MetadataSources(registry);

                Reflections reflections = new Reflections("org.example.domains");
                reflections.getTypesAnnotatedWith(Entity.class)
                        .forEach(aClass -> metadataSources.addAnnotatedClassName(aClass.getName()));

                Metadata metadata = metadataSources.getMetadataBuilder().build();
                return metadata.getSessionFactoryBuilder().build();


            } catch (Exception e) {

                if (Objects.nonNull(registry)){

                    StandardServiceRegistryBuilder.destroy(registry);
                }

                throw new RuntimeException("Hibernate exception %s".formatted(e.getMessage()));
            }
        }
        return sessionFactory;
    }

    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }
}
