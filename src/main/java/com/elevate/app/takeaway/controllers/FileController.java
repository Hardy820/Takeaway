package com.elevate.app.takeaway.controllers;

import com.elevate.app.takeaway.model.ResponseModel;
import com.elevate.app.takeaway.model.DBFile;
import com.elevate.app.takeaway.service.DBFileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class FileController {
    @Autowired
    private DBFileStorageService dbFileStorageService;

    @PostMapping("/uploadFile/{userId}")
    public ResponseModel uploadFile(@RequestParam("file") MultipartFile file, long userId) {
        dbFileStorageService.storeFile(file, userId);
        ResponseModel responseModel = new ResponseModel();
        responseModel.message = "Successfully uploaded";
        responseModel.responseCode = HttpStatus.OK.value();
        return responseModel;
    }

    @PostMapping("/uploadMultipleFiles/{userId}")
    public List<ResponseModel> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files,@PathVariable long userId) {
        return Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(file, userId))
                .collect(Collectors.toList());
    }

    @GetMapping("/downloadFile/{userId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable long userId) {
        // Load file from database
        DBFile dbFile = dbFileStorageService.getFile(userId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(dbFile.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getFileName() + "\"")
                .body(new ByteArrayResource(dbFile.getData()));
    }

}
