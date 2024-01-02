package ua.univer.custodianNew.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.univer.custodianNew.util.FileDownloadService;

import java.io.IOException;

@RestController
@RequestMapping(value = "/api")
public class FileDownloadController {

    Logger logger = LoggerFactory.getLogger(FileDownloadController.class);

    private final FileDownloadService downloadService;

    public FileDownloadController(FileDownloadService downloadUtil) {
        this.downloadService = downloadUtil;
    }

    @GetMapping("/downloadFile/{fileCode}")
    public ResponseEntity<?> downloadFile(@PathVariable("fileCode") String fileCode) {

        logger.info("Download file. FileCode = " + fileCode);

        Resource resource = null;
        try {
            resource = downloadService.getFileFromStorage(Integer.valueOf(fileCode));
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }

        if (resource == null) {
            return new ResponseEntity<>("File not found", HttpStatus.NOT_FOUND);
        }

        String contentType = "application/octet-stream";
        String headerValue = "attachment; filename=\"" + resource.getFilename() + "\"";

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
                .body(resource);
    }

    @GetMapping(
            value = "/showPDF/{fileName}",
            produces = MediaType.APPLICATION_PDF_VALUE
    )
    public @ResponseBody byte[] showPdfWithMediaType(@PathVariable("fileName") String fileName) {
        logger.info("Show PDF. FileCode = " + fileName);

        return downloadService.getByteArrFromStorage(fileName);
    }


    @GetMapping(value = "/downloadPDF/{fileName}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<?> getFileViaByteArrayResource(@PathVariable("fileName") String fileName) {

        logger.info("Download PDF. FileName = " + fileName);

        byte[] byteArr = downloadService.getByteArrFromStorage(fileName);
        if (byteArr == null){
            logger.info("File not found. Method downloadPDF.");
            return new ResponseEntity<>("File not found", HttpStatus.NOT_FOUND);
        }

        ByteArrayResource resource = new ByteArrayResource(byteArr);
        //logger.info("Content Length = " + resource.contentLength());
        String headerValue = "attachment; filename=\"" + fileName + "\"";

        downloadService.deleteFromStorage(fileName);
        logger.info("Delete from Storage fileName = " + fileName);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
                .body(resource);
    }


}
