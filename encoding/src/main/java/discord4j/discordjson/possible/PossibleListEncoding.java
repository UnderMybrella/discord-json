package discord4j.discordjson.possible;

import org.immutables.encode.Encoding;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Encoding
public class PossibleListEncoding<T> {

    @Encoding.Impl(virtual = true)
    private Possible<List<T>> possible;

    private final List<T> value = possible.toOptional().orElse(null);
    private final boolean absent = possible.isAbsent();

    @Encoding.Expose
    Possible<List<T>> get() {
        return absent ? discord4j.discordjson.possible.Possible.absent() :
                discord4j.discordjson.possible.Possible.of(value);
    }

    @Encoding.Naming("is*Present")
    boolean isPresent() {
        return !absent;
    }

    @Encoding.Naming("*OrElse")
    List<T> orElse(List<T> defaultValue) {
        return !absent ? value : defaultValue;
    }

    //    @Encoding.Of
    //    @Nullable
    //    static <T> List<T> init(Possible<List<T>> possible) {
    //        return Objects.requireNonNull(possible).toOptional().<List<T>>map(ArrayList::new).orElse(null);
    //    }

    @Override
    public String toString() {
        return Objects.toString(value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    public boolean equals(PossibleListEncoding<T> obj) {
        return Objects.equals(value, obj.value);
    }

    @Encoding.Builder
    static class Builder<T> {

        private List<T> list = null;

        @Encoding.Naming(standard = Encoding.StandardNaming.ADD)
        @Encoding.Init
        void add(T element) {
            getOrCreate().add(element);
        }

        @Encoding.Naming(standard = Encoding.StandardNaming.ADD_ALL)
        @Encoding.Init
        void addAll(List<T> elements) {
            getOrCreate().addAll(elements);
        }

        @Encoding.Build
        Possible<List<T>> build() {
            return this.list == null ? discord4j.discordjson.possible.Possible.absent() :
                    discord4j.discordjson.possible.Possible.of(this.list);
        }

        @Encoding.Init
        @Encoding.Copy
        void set(Possible<List<T>> elements) {
            this.list = null;

            elements.toOptional().ifPresent(e -> getOrCreate().addAll(e));
        }

        @Encoding.Init
        void setValue(List<T> elements) {
            this.list = new ArrayList<>(elements);
        }

        private List<T> getOrCreate() {
            if (this.list == null) {
                this.list = new ArrayList<>();
            }
            return this.list;
        }
    }
}
