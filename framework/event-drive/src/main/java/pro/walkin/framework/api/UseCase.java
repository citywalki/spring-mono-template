package pro.walkin.framework.api;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public interface UseCase<C extends Command<? extends R>, R> {

    R handle(@Valid @NotNull C command);

    default boolean predicate(@NotNull C command) {
        return true;
    }

    default int order() {
        return 1;
    }

}
