package br.com.caelum.vraptor.plugin.cdi;

import javax.inject.Qualifier;

public interface ServiceLocator {

    <T> T get(Class<T> clazz, Qualifier... qualifiers);
}
