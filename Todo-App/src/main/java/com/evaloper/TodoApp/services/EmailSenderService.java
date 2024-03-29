package com.evaloper.TodoApp.services;

import com.evaloper.TodoApp.dto.EmailDetails;
import org.springframework.stereotype.Service;

public interface EmailSenderService {
    void sendEmailAlert(EmailDetails emailDetails);
}
