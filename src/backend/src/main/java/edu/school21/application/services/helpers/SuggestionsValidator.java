package edu.school21.application.services.helpers;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class SuggestionsValidator {

    private static final double POSITIVE_MIN = 0;
    private static final double PERCENT_MAX = 100;

    public void validateNumberBetween(
            final String name,
            final int number,
            final int min,
            final int max
    ) {
        if (number < min || number > max) {
            throw new IllegalStateException(String.format(
                    "%s не находится в промежутке от %d до %d", name, min, max
            ));
        }
    }

    public void validateNumberMin(
            final String name,
            final int number,
            final int min
    ) {
        if (number < min) {
            throw new IllegalStateException(String.format(
                    "%s должно быть больше %d", name, min
            ));
        }
    }

    public void validatePercent(final String name, final double percent) {
        if (percent < POSITIVE_MIN || percent > PERCENT_MAX) {
            throw new IllegalStateException(String.format(
                    "%s не находится в промежутке от 0 до 100", name
            ));
        }
    }

    public void validateDate(
            final String startDate,
            final String endDate
    ) {
        final DateTimeFormatter pattern = DateTimeFormatter.ofPattern("uuuu-MM-dd") ;
        final LocalDate start = LocalDate.parse(startDate, pattern);
        final LocalDate end = LocalDate.parse(endDate, pattern);

        if (start.isAfter(end)) {
            throw new IllegalStateException(String.format(
                    "Начальная дата находится позже конечной start = %s, end = %s", startDate, endDate
            ));
        }
    }
}
