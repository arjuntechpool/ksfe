package com.techpool.ksfe.service;

import com.techpool.ksfe.dto.EmployeeQueueDTO;
import com.techpool.ksfe.repository.TransRequestChildRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransferService {

    @Autowired
    private TransRequestChildRepository transRequestChildRepository;

    public List<EmployeeQueueDTO> generateQueueList(Integer officeId) {
        List<Object[]> results = transRequestChildRepository.findQueueListByOfficeId(officeId);
         // Map the results to EmployeeQueueDTO
        return results.stream()
            .map(row -> new EmployeeQueueDTO(
                (String) row[0], // employee_code
                (String) row[1], // employee_name
                (Integer) row[2], // preferred_office
                (Integer) row[3], // preference_order
                (Integer) row[4]  // priority_value
            ))
            .collect(Collectors.toList());
    }
}
