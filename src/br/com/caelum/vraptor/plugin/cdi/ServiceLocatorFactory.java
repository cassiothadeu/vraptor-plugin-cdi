package br.com.caelum.vraptor.plugin.cdi;

import javax.enterprise.inject.spi.BeanManager;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.ComponentFactory;

@Component
@ApplicationScoped
public class ServiceLocatorFactory
    implements ComponentFactory<ServiceLocator> {

    private static final String BEAN_MANAGER = "java:comp/BeanManager";

    @Override
    public ServiceLocator getInstance() {

        try {
            InitialContext ctx = new InitialContext();
            BeanManager beanManager = (BeanManager) ctx.lookup(BEAN_MANAGER);
            return new CdiServiceLocator(beanManager);

        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }
}
