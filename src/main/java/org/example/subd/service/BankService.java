package org.example.subd.service;


import lombok.RequiredArgsConstructor;
import org.example.subd.model.mapper.BankMapper;
import org.example.subd.repository.BankRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class BankService {

    private final BankRepository bankRepository;
    private final BankMapper bankMapper;

}
