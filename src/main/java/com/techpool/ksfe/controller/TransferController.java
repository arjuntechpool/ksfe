package com.techpool.ksfe.controller;

import com.techpool.ksfe.dto.EmployeeQueueDTO;
import com.techpool.ksfe.entity.TransRequestChild;
import com.techpool.ksfe.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transfer")
public class TransferController {

    @Autowired
    private TransferService transferService;

    @GetMapping("/queue")
    public ResponseEntity<List<EmployeeQueueDTO>> getQueueList(@RequestParam Integer officeId) {
        List<EmployeeQueueDTO> queueList = transferService.generateQueueList(officeId);
        return ResponseEntity.ok(queueList);
    }
}