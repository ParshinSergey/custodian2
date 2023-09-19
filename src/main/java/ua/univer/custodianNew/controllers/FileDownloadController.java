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
import ua.univer.custodianNew.util.FileDownloadUtil;

import java.io.IOException;

@RestController
@RequestMapping(value = "/api")
public class FileDownloadController {

    Logger logger = LoggerFactory.getLogger(FileDownloadController.class);

    private final FileDownloadUtil downloadUtil;

    public FileDownloadController(FileDownloadUtil downloadUtil) {
        this.downloadUtil = downloadUtil;
    }

    @GetMapping("/downloadFile/{fileCode}")
    public ResponseEntity<?> downloadFile(@PathVariable("fileCode") String fileCode) {

        logger.info("Download file. FileCode = " + fileCode);
        //FileDownloadUtil downloadUtil = new FileDownloadUtil();

        Resource resource = null;
        try {
            resource = downloadUtil.getFileFromStorage(Integer.valueOf(fileCode));
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
    public @ResponseBody byte[] getPdfWithMediaType(@PathVariable("fileName") String fileName) throws IOException {
        logger.info("Show PDF. FileCode = " + fileName);

        return downloadUtil.getByteArrFromStorage(fileName);
    }


    @GetMapping(value = "/downloadPDF/{fileName}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<?> getFileViaByteArrayResource(@PathVariable("fileName") String fileName) throws IOException {

        logger.info("Download file. FileName = " + fileName);
        ByteArrayResource resource = new ByteArrayResource(downloadUtil.getByteArrFromStorage(fileName));

        String contentType = "application/octet-stream";
        String headerValue = "attachment; filename=\"" + fileName + "\"";

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
                .body(resource);
    }


}
