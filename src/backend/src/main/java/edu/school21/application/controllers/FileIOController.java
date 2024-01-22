package edu.school21.application.controllers;

import edu.school21.application.annotations.AccessControl;
import edu.school21.application.enums.Access;
import edu.school21.application.enums.InfoMessages;
import edu.school21.application.exceptions.ValidationGraphQLException;
import edu.school21.application.services.FileIOService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Controller
@AllArgsConstructor
@AccessControl(Access.PRIVATE)
public class FileIOController {
    private final FileIOService service;

    @PostMapping("/import")
    @ResponseBody
    public void fileUpload(
            @RequestParam("table_name") final String table,
            @RequestParam("file") final MultipartFile file
    ) {
        log.info("POST /import?file_name={}&table_name={}", file.getName(), table);
        final InfoMessages message = service.importToTable(table, file);
        if (message != InfoMessages.INPUT_FILE_SUCCESS) {
            log.warn("Import error: {}", message.getName());
            throw new ValidationGraphQLException(message.getName());
        }
        log.info("Import success");
    }


    @PostMapping("/exportFromTable")
    @ResponseBody
    public ResponseEntity<Resource> exportFromTable(@RequestParam("table_name") final String table) {
        log.info("POST /exportFromTable?&table_name={}", table);
        final InfoMessages message = service.exportFromTable(table);
        if (message != InfoMessages.OUTPUT_FILE_SUCCESS) {
            log.warn("Export error: {}", message.getName());
            throw new ValidationGraphQLException(message.getName());
        } else {
            log.info("Export success");
        }
        return service.getFileAsResponse(table);
    }

    @PostMapping("/exportFromFunction")
    @ResponseBody
    public ResponseEntity<Resource> exportFromFunction(@RequestParam("file_name") final String fileName) {
        log.info("POST /exportFromFunction?file_name={}", fileName);
        final InfoMessages message = service.exportFromFunction(fileName);
        if (message != InfoMessages.OUTPUT_FILE_SUCCESS) {
            log.warn("Export error: {}", message.getName());
            throw new ValidationGraphQLException(message.getName());
        } else {
            log.info("Export success");
        }
        return service.getFileAsResponse(fileName);
    }

}
