package br.com.caelum.vraptor.plugin.cdi;

import java.util.Set;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Qualifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CdiServiceLocator
    implements ServiceLocator {

    private transient Logger logger = LoggerFactory.getLogger(CdiServiceLocator.class);
    private final BeanManager beanManager;

    public CdiServiceLocator(BeanManager beanManager) {
        this.beanManager = beanManager;
    }

    @SuppressWarnings("unchecked")
    public <T> T get(Class<T> clazz, Qualifier... qualifiers) {
        Set<Bean<?>> beans = beanManager.getBeans(clazz, qualifiers);
        logger.info("found {} instances for {}", beans.size(), clazz);

        Bean<?> bean = (Bean<?>) beans.iterator().next();

        CreationalContext<?> ctx = beanManager.createCreationalContext(bean);
        return (T) beanManager.getReference(bean, clazz, ctx);
    }
}
