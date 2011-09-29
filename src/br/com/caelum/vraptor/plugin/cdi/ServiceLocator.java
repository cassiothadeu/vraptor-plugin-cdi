package br.com.caelum.vraptor.plugin.cdi;

import javax.enterprise.inject.Default;
import javax.inject.Qualifier;

/**
 * Class responsible to interact directly with the container to get CDI Beans.
 * 
 * @author Otávio Scherer Garcia
 */
public interface ServiceLocator {

    /**
     * Return a bean for the given type and qualifiers. If no qualifiers are given, the {@link Default} qualifier is
     * assumed.
     * 
     * @param clazz the required bean type
     * @param qualifiers the required qualifiers
     * @return a bean for the given type and qualifiers
     */
    <T> T get(Class<T> clazz, Qualifier... qualifiers);
}
