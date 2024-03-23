package pro.walkin.framework.api;

public interface Command<R> {

    default R fire() {
        return CommandOperations.get().fire(this);
    }

}
