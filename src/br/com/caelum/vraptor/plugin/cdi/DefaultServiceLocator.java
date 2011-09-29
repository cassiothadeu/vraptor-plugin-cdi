package br.com.caelum.vraptor.plugin.cdi;

import static org.slf4j.LoggerFactory.getLogger;

import java.util.Arrays;
import java.util.Set;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Qualifier;

import org.slf4j.Logger;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;

/**
 * Default implementation for {@link ServiceLocator}.
 * 
 * @author Otávio Scherer Garcia
 */
@Component
@ApplicationScoped
public class DefaultServiceLocator
    implements ServiceLocator {

    private static final Logger logger = getLogger(DefaultServiceLocator.class);
    private final BeanManager beanManager;

    public DefaultServiceLocator(BeanManager beanManager) {
        this.beanManager = beanManager;
    }

    public <T> T get(Class<T> clazz, Qualifier... qualifiers) {
        if (logger.isDebugEnabled()) {
            logger.debug("looking for bean {} with {} in CDI context", clazz, Arrays.toString(qualifiers));
        }

        Set<Bean<?>> beans = beanManager.getBeans(clazz, qualifiers);
        logger.debug("found {} instances for {}", beans.size(), clazz);

        Bean<?> bean = (Bean<?>) beans.iterator().next();

        CreationalContext<?> ctx = beanManager.createCreationalContext(bean);
        return clazz.cast(beanManager.getReference(bean, clazz, ctx));
    }
}
