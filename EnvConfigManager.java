import java.util.Optional;

public class EnvConfigManager implements ConfigManager {
    @Override
    public String get(String key) {
        return Optional.ofNullable(System.getenv(key)).orElse("UNDEFINED");
    }
}
