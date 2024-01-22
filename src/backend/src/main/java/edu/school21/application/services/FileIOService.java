package edu.school21.application.services;

import edu.school21.application.enums.Directory;
import edu.school21.application.enums.InfoMessages;
import edu.school21.application.repositories.FileIORepository;
import edu.school21.application.services.helpers.ExportMapper;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class FileIOService {

    private final FileIORepository repository;
    private final FunctionsService service;
    private final ExportMapper mapper;

    public InfoMessages importToTable(final String tableName, final MultipartFile file) {
        if (file.isEmpty()) {
            return InfoMessages.INPUT_FILE_ERROR_INVALID_DATA;
        }

        clearDirectory(Directory.IMPORT);
        try {
            final byte[] bytes = file.getBytes();
            final BufferedOutputStream stream = new BufferedOutputStream(
                    new FileOutputStream(getPathForDirectory(Directory.IMPORT) + tableName + ".csv")
            );
            stream.write(bytes);
            stream.close();
            final boolean status = repository.importToTable(tableName);
            return status ? InfoMessages.INPUT_FILE_SUCCESS : InfoMessages.INPUT_FILE_ERROR_SOMETHING_WRONG;
        } catch (final Exception e) {
            return prepareImportErrorMessage(e);
        }
    }

    public InfoMessages exportFromTable(final String tableName) {
        clearDirectory(Directory.EXPORT);
        if (!repository.tableExists(tableName)) {
            return InfoMessages.OUTPUT_FILE_ERROR_INVALID_TABLE;
        }
        final boolean status = repository.exportFromTable(tableName);
        return status ? InfoMessages.OUTPUT_FILE_SUCCESS : InfoMessages.OUTPUT_FILE_ERROR;
    }

    public InfoMessages exportFromFunction(final String tableName) {
        if (Objects.isNull(service.getLastResult())) {
            return InfoMessages.OUTPUT_FILE_EMPTY;
        }
        clearDirectory(Directory.EXPORT);
        try (final FileOutputStream stream = new FileOutputStream(getExportFileByName(tableName).toString())) {
            final List lastResult = service.getLastResult();
            if(lastResult.isEmpty()) {
                return InfoMessages.OUTPUT_FILE_EMPTY;
            }
            stream.write(mapper.convert(lastResult, ','));
        } catch (final IOException e) {
            return InfoMessages.OUTPUT_FILE_ERROR;
        }
        return InfoMessages.OUTPUT_FILE_SUCCESS;
    }

    public ResponseEntity<Resource> getFileAsResponse(final String name) {
        final File file = getExportFileByName(name).toFile();

        try {
            final InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            final HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, attachmentParameters(name));
            return ResponseEntity.ok()
                                 .headers(headers)
                                 .contentLength(file.length())
                                 .contentType(MediaType.parseMediaType("application/octet-stream"))
                                 .body(resource);
        } catch (final FileNotFoundException e) {
            throw new IllegalStateException(e);
        }
    }

    private Path getExportFileByName(final String table) {
        return new File(getPathForDirectory(Directory.EXPORT) + table + ".csv").toPath();
    }

    private InfoMessages prepareImportErrorMessage(final Exception e) {
        if(e.getMessage().contains("duplicate key value violates")) {
            return InfoMessages.INPUT_FILE_ERROR_DUPLICATE;
        } else if ("ERROR: invalid input syntax".equalsIgnoreCase(e.getMessage())) {
            return InfoMessages.INPUT_FILE_ERROR_INVALID_DATA;
        } else {
            return InfoMessages.INPUT_FILE_ERROR_SOMETHING_WRONG;
        }
    }

    private void clearDirectory(final Directory directory) {
        File file = new File(getPathForDirectory(directory));
        try {
            for (final String i : Objects.requireNonNull(file.list())) {
                file = new File(getPathForDirectory(directory) + i);
                file.delete();
            }
        } catch (final NullPointerException e) {
            // DO NOTHING
        }
    }

    private String getPathForDirectory(final Directory directory) {
        return new File("").getAbsolutePath() + directory.getName();
    }

    private String attachmentParameters(final String fileName) {
        return "attachment;filename=" +
               URLEncoder.encode(fileName, StandardCharsets.UTF_8) +
               ".csv";
    }
}
