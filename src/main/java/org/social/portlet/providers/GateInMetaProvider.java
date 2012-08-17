package org.social.portlet.providers;

import javax.inject.Provider;

import juzu.inject.ProviderFactory;

import org.exoplatform.container.PortalContainer;

public class GateInMetaProvider implements ProviderFactory {

  @Override
  public <T> Provider<? extends T> getProvider(final Class<T> clazz) {
    
    return new Provider<T>() {
      
      @Override
      public T get() {
        @SuppressWarnings("unchecked")
        T t = (T) PortalContainer.getInstance().getComponentInstanceOfType(clazz);
        return t;
      }
    };
  }

}