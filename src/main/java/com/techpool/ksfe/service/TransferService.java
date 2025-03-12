package com.techpool.ksfe.service;

import com.techpool.ksfe.entity.TransRequestChild;
import com.techpool.ksfe.repository.TransRequestChildRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransferService {

    @Autowired
    private TransRequestChildRepository transRequestChildRepository;

    public List<TransRequestChild> generateQueueList(Integer officeId) {
        return transRequestChildRepository.findQueueListByOfficeId(officeId);
    }
}
