package com.kyc.catalogs.config;

import com.kyc.catalogs.model.properties.CatalogInfo;
import com.kyc.catalogs.properties.CatalogProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

public class CatalogsBeanConfig implements EnvironmentAware, InitializingBean,BeanDefinitionRegistryPostProcessor {

    public static final Logger LOGGER = LoggerFactory.getLogger(CatalogsBeanConfig.class);

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
