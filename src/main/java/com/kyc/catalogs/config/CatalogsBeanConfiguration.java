package com.kyc.catalogs.config;

import com.kyc.catalogs.controllers.CatalogController;
import com.kyc.catalogs.model.properties.CatalogInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Map;

//@Configuration
public class CatalogsBeanConfiguration implements EnvironmentAware, InitializingBean,BeanDefinitionRegistryPostProcessor {

    public static final Logger LOGGER = LogManager.getLogger(CatalogsBeanConfiguration.class);

    private Environment environment;

    private CatalogProperties props;

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {

        if(props!=null){

            for(CatalogInfo catalog : props.getCatalogs()){
                LOGGER.info("Se cargo catalogo {}",catalog.getId());
            }
        }

    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }

    @Override
    public void afterPropertiesSet() throws Exception {

        BindResult<CatalogProperties> binder = Binder.get(environment).bind("kyc-config",CatalogProperties.class);
        props = binder.get();
    }
}
