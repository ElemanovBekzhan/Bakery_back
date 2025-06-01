package org.example.subd.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequestMapping("/api/credits")
@RequiredArgsConstructor
public class BankController {

    @GetMapping("/pay/{id}")
    public String paymentHistoryForPay(@PathVariable UUID id,
                                       @RequestParam(value = "payment_date", required = false)
                                       @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate Date,
                                       ) {


    }
}
