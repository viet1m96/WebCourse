package test;

import jakarta.enterprise.context.spi.CreationalContext;
import jakarta.enterprise.inject.spi.Bean;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CustomScopeContextHolder implements Serializable {

    private static CustomScopeContextHolder INSTANCE;
    private Map<Class, CustomScopeInstance> beans;

    private CustomScopeContextHolder() {
        beans = Collections.synchronizedMap(new HashMap<Class, CustomScopeInstance>());
    }

    public synchronized static CustomScopeContextHolder getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CustomScopeContextHolder();
        }
        return INSTANCE;
    }

    public Map<Class, CustomScopeInstance> getBeans() {
        return beans;
    }

    public CustomScopeInstance getBean(Class type) {
        return getBeans().get(type);
    }

    public void putBean(CustomScopeInstance customInstance) {
        getBeans().put(customInstance.bean.getBeanClass(), customInstance);
    }

    void destroyBean(CustomScopeInstance customScopeInstance) {
        getBeans().remove(customScopeInstance.bean.getBeanClass());
        customScopeInstance.bean.destroy(customScopeInstance.instance, customScopeInstance.ctx);
    }

    public static class CustomScopeInstance<T> {
        Bean<T> bean;
        CreationalContext<T> ctx;
        T instance;
    }
}
