package pages;

import java.util.Map;
import java.util.Optional;

public interface Convertible {
    String FIRSTNAME = "firstname",
            LASTNAME = "lastname",
            EMAIL = "email",
            GROUP_NAME = "group_name";

    default void addNotNullableValueToMap(Map<String, Object> mappedModel, Optional<String> modelData, String header) {
        modelData.ifPresent(val -> mappedModel.put(header, val));
    }

    Map<String, Object> getMapFromModel();
}
