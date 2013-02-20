package br.com.caelum.vraptor.plugin.cdi;

import static org.slf4j.LoggerFactory.getLogger;

import javax.enterprise.inject.spi.BeanManager;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.slf4j.Logger;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.ComponentFactory;
import br.com.caelum.vraptor.ioc.ComponentRegistrationException;

/**
 * A simple factory to get {@link BeanManager} from JNDI tree.
 * 
 * @author Otávio Scherer Garcia
 */
@Component
@ApplicationScoped
public class BeanManagerFactory implements ComponentFactory<BeanManager> {

	private static final Logger logger = getLogger(BeanManagerFactory.class);

	/**
	 * JNDI address for {@link BeanManager}.
	 */
	private static final String BEAN_MANAGER = "java:comp/BeanManager";

	public BeanManager getInstance() {
		logger.debug("looking for BeanManager in JNDI tree");

		try {
			return (BeanManager) new InitialContext().lookup(BEAN_MANAGER);
		} catch (NamingException e) {
			logger.error("BeanManager not found", e);
			throw new ComponentRegistrationException(
					"Can't find BeanManager in JNDI", e);
		}
	}
}
