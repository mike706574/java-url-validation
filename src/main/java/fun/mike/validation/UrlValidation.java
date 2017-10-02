package fun.mike.validation;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class UrlValidation {
    private final String url;
    private final boolean isValid;
    private final List<String> problems;

    @Override
    public String toString() {
        return "UrlValidation{" +
                "url='" + url + '\'' +
                ", isValid=" + isValid +
                ", problems=" + problems +
                '}';
    }

    private UrlValidation(String url, boolean isValid, List<String> problems) {
        this.url = url;
        this.isValid = isValid;
        this.problems = new LinkedList<>(problems);
    }

    public static UrlValidation valid(String url) {
        return new UrlValidation(url, true, new LinkedList<>());
    }

    public static UrlValidation invalid(String url, List<String> problems) {
        return new UrlValidation(url, false, problems);
    }

    public static UrlValidation invalid(String url, String problem) {
        return new UrlValidation(url, false, Arrays.asList(problem));
    }

    public boolean isValid() {
        return isValid;
    }

    public UrlValidation orThrow(String prefix) {
        if (isValid) {
            return this;
        }

        String reason = getReason()
                .orElseThrow(() -> {
                    String message = String.format("URL \"%s\" is invalid, but reason generation failed.");
                    return new IllegalStateException(message);
                });
        String message = String.format("%s: %s", prefix, reason);
        throw new IllegalArgumentException(message);
    }

    public Optional<String> getReason() {
        if (isValid) {
            return Optional.empty();
        }

        String reason;
        if (problems.size() == 1) {
            reason = problems.get(0);
        } else {
            reason = String.format("Multiple problems:\n%s", String.join("\n", problems));
        }

        return Optional.of(reason);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UrlValidation that = (UrlValidation) o;

        if (isValid != that.isValid) return false;
        if (url != null ? !url.equals(that.url) : that.url != null) return false;
        return problems != null ? problems.equals(that.problems) : that.problems == null;
    }

    @Override
    public int hashCode() {
        int result = url != null ? url.hashCode() : 0;
        result = 31 * result + (isValid ? 1 : 0);
        result = 31 * result + (problems != null ? problems.hashCode() : 0);
        return result;
    }
}
