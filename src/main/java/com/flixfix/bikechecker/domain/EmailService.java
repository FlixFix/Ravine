package com.flixfix.bikechecker.domain;

public interface EmailService {
    void sendEmail(String size);

    void sendStartupEmail();
}
