package pro.walkin.framework.api;

public class UseCaseMeta {

    public static final String KEY_COMMAND_SERVICE = "commandServiceBean";

    private Class<? extends Command<?>> command;

    private String service;

    private int order;

    public Class<? extends Command<?>> getCommand() {
        return command;
    }

    public void setCommand(Class<? extends Command<?>> command) {
        this.command = command;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

}
