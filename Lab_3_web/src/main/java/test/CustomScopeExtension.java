package test;

import jakarta.enterprise.event.Observes;
import jakarta.enterprise.inject.spi.AfterBeanDiscovery;
import jakarta.enterprise.inject.spi.BeforeBeanDiscovery;
import jakarta.enterprise.inject.spi.Extension;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;

public class CustomScopeExtension implements Extension, Serializable {
    public void addScope(@Observes final BeforeBeanDiscovery event) {
        event.addScope(TaskScope.class, true, false);
    }
    public void registerContext(@Observes final AfterBeanDiscovery event) {
        event.addContext(new CustomScopeContext());
    }
}
