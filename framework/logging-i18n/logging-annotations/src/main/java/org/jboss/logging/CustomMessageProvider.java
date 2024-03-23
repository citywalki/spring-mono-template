package org.jboss.logging;

import java.util.ServiceLoader;

public interface CustomMessageProvider {

    ServiceLoader<CustomMessageProvider> serviceLoader = ServiceLoader.load(CustomMessageProvider.class);

    CustomMessageProvider PROVIDER = load();

    static CustomMessageProvider load() {
        if (serviceLoader.iterator().hasNext()) {
            return serviceLoader.iterator().next();
        }

        return (messageCode, messageName) -> null;
    }

    String custom(String messageCode, String messageName);

}
