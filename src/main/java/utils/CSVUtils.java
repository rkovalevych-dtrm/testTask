package utils;

import io.qameta.allure.Step;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

import static java.util.stream.Collectors.toList;

@Slf4j
@UtilityClass
public class CSVUtils {

    @Step("Convert xls to csv")
    public static void insertDataIntoCSVFile(Set<Map<String, Object>> listOfRows, File outputFile) {
        try {
            List<String> headers = listOfRows.stream().flatMap(map -> map.keySet().stream()).distinct().collect(toList());

            final StringBuffer sb = new StringBuffer();
            for (int i = 0; i < headers.size(); i++) {
                sb.append(headers.get(i));
                sb.append(i == headers.size() - 1 ? "\n" : ",");
            }
            for (Map<String, Object> map : listOfRows) {
                for (int i = 0; i < headers.size(); i++) {
                    sb.append(map.get(headers.get(i)));
                    sb.append(i == headers.size() - 1 ? "\n" : ",");
                }
            }
            FileOutputStream fos = new FileOutputStream(outputFile);
            fos.write(sb.toString().getBytes());
            fos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
