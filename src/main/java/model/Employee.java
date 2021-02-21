package model;

import lombok.Builder;
import lombok.Data;
import pages.Convertible;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Data
@Builder
public class Employee implements Convertible {

    String firstname;
    String lastname;
    String email;
    String group_name;

    @Override
    public Map<String, Object> getMapFromModel() {
        Map<String, Object> map = new HashMap<>();
        addNotNullableValueToMap(map, Optional.ofNullable(firstname), FIRSTNAME);
        addNotNullableValueToMap(map, Optional.ofNullable(lastname), LASTNAME);
        addNotNullableValueToMap(map, Optional.ofNullable(email), EMAIL);
        addNotNullableValueToMap(map, Optional.ofNullable(group_name), GROUP_NAME);

        return map;
    }
}
