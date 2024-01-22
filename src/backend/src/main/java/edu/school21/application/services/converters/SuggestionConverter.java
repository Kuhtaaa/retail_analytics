package edu.school21.application.services.converters;

import edu.school21.application.graphql.models.SuggestionModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SuggestionConverter {

    public List<SuggestionModel> convertSuggestionForAverageTicketGrowth(final List<Object[]> data) {
        return data
                .stream()
                .map(raw -> SuggestionModel.suggestionForAverageTicketGrowth(
                        Integer.parseInt(String.valueOf(raw[0])),
                        Float.parseFloat(String.valueOf(raw[1])),
                        String.valueOf(raw[2]),
                        Float.parseFloat(String.valueOf(raw[3]))
                )).collect(Collectors.toList());
    }

    public List<SuggestionModel> convertSuggestionForIncreasingFrequencyOfVisits(final List<Object[]> data) {
        return data
                .stream()
                .map(raw -> SuggestionModel.suggestionForIncreasingFrequencyOfVisits(
                        Integer.parseInt(String.valueOf(raw[0])),
                        String.valueOf(raw[1]),
                        String.valueOf(raw[2]),
                        Integer.parseInt(String.valueOf(raw[3])),
                        String.valueOf(raw[4]),
                        Float.parseFloat(String.valueOf(raw[5]))
                )).collect(Collectors.toList());
    }

    public List<SuggestionModel> convertSuggestionForOffersFocusedOnCrossSelling(final List<Object[]> data) {
        return data
                .stream()
                .map(raw -> SuggestionModel.suggestionForOffersFocusedOnCrossSelling(
                        Integer.parseInt(String.valueOf(raw[0])),
                        String.valueOf(raw[1]),
                        Float.parseFloat(String.valueOf(raw[2]))
                )).collect(Collectors.toList());
    }
}
