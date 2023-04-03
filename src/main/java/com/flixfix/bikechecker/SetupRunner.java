package com.flixfix.bikechecker;

import com.flixfix.bikechecker.domain.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Slf4j
@Component
public class SetupRunner implements CommandLineRunner {

    private final EmailService emailService;

    @Override
    public void run(String... args) {
        emailService.sendStartupEmail();
    }
}
