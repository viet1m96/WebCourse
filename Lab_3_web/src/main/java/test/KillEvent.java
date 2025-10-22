package test;

public final class KillEvent {
    private final Class<?> beanType;

    public KillEvent(Class<?> beanType) {
        this.beanType = beanType;
    }

    public Class<?> getBeanType() {
        return beanType;
    }
}