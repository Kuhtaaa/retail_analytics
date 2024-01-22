package edu.school21.application.services.helpers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
public class ExportMapper {

    public byte[] convert (
            final List<Object[]> rawData,
            final char separator
    ) {
        final StringBuilder temp = new StringBuilder();
        for (final Object[] rawDatum : rawData) {
            for (int j = 0; j < rawDatum.length; j++) {
                temp.append(rawDatum[j]);
                if (j != rawDatum.length - 1) {
                    temp.append(separator);
                }
            }
            temp.append('\n');
        }
        return temp.toString().getBytes();
    }
}
