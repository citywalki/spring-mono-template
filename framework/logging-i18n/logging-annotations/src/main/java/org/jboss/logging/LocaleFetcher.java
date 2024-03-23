package org.jboss.logging;

import java.util.ServiceLoader;

public interface LocaleFetcher {

    ServiceLoader<LocaleFetcher> serviceLoader = ServiceLoader.load(LocaleFetcher.class);

    LocaleFetcher PROVIDER = load();

    static LocaleFetcher load() {
        if (serviceLoader.iterator().hasNext()) {
            return serviceLoader.iterator().next();
        }

        return () -> "en";
    }

    String fetch();

}
