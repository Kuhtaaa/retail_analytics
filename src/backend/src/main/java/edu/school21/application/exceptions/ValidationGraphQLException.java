package edu.school21.application.exceptions;

import graphql.ErrorClassification;
import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.io.Serial;
import java.util.List;

public class ValidationGraphQLException extends RuntimeException implements GraphQLError {

    @Serial
    private static final long serialVersionUID = -4125741448623935787L;

    public ValidationGraphQLException(final String message) {
        super(message);
    }

    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    public ErrorClassification getErrorType() {
        return ErrorType.ValidationError;
    }
}
