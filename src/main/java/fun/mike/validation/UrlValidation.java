package fun.mike.validation;

import java.util.Optional;

public class UrlValidation {
    private final String value;
    private final boolean isValid;
    private final String problem;

    private UrlValidation(String value, boolean isValid, String problem) {
        this.value = value;
        this.isValid = isValid;
        this.problem = problem;
    }

    public static UrlValidation valid(String value) {
        return new UrlValidation(value, true, null);
    }

    public static UrlValidation invalid(String value, String problem) {
        return new UrlValidation(value, false, problem);
    }

    @Override
    public String toString() {
        return "UrlValidation{" +
                "value='" + value + '\'' +
                ", isValid=" + isValid +
                ", problem='" + problem + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UrlValidation that = (UrlValidation) o;

        if (isValid != that.isValid) return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;
        return problem != null ? problem.equals(that.problem) : that.problem == null;
    }

    @Override
    public int hashCode() {
        int result = value != null ? value.hashCode() : 0;
        result = 31 * result + (isValid ? 1 : 0);
        result = 31 * result + (problem != null ? problem.hashCode() : 0);
        return result;
    }

    public boolean isValid() {
        return isValid;
    }

    public UrlValidation orThrow(String prefix) {
        if (isValid) {
            return this;
        }

        String problem = getProblem().orElseThrow(() -> {
            String message = String.format("\"%s\" is invalid, but no problem found.");
            return new IllegalStateException(message);
        });
        String message = String.format("%s: %s", prefix, problem);
        throw new IllegalArgumentException(message);
    }

    public Optional<String> getProblem() {
        if (isValid) {
            return Optional.empty();
        }

        return Optional.of(problem);
    }
}
