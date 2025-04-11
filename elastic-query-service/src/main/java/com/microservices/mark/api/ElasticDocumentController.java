package com.microservices.mark.api;

import com.microservices.mark.model.ElasticQueryServiceRequestModel;
import com.microservices.mark.model.ElasticQueryServiceResponseModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/documnets")
public class ElasticDocumentController {

    @GetMapping
    public ResponseEntity<List<ElasticQueryServiceResponseModel>> getAllDocuments() {
        List<ElasticQueryServiceResponseModel> response = new ArrayList<>();
        log.info("Elasticsearch returned {} of documents", response.size());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ElasticQueryServiceResponseModel> getDocumentById(@PathVariable String id) {
        ElasticQueryServiceResponseModel response = ElasticQueryServiceResponseModel.builder()
                .id(id)
                .build();

        log.info("Elasticsearch returned with id {}", id);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/get-document-by-text")
    public ResponseEntity<List<ElasticQueryServiceResponseModel>> getDocumentByText(@RequestBody ElasticQueryServiceRequestModel requestModel) {
        List<ElasticQueryServiceResponseModel> response = new ArrayList<>();
        response.add(
                ElasticQueryServiceResponseModel.builder()
                        .text(requestModel.getText())
                        .build()
        );

        log.info("Elasticsearch returned {} of documents", response.size());

        return ResponseEntity.ok(response);
    }
}
