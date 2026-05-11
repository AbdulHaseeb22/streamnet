package com.streamnet.service;

import com.streamnet.commons.event.SendEmailEvent;

public interface EmailService {
    void sendEmail(SendEmailEvent emailEvent);
}
