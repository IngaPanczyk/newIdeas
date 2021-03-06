package com.kodilla.newideas.domain;


import lombok.Getter;

import java.io.IOException;
import java.io.InputStream;

@Getter
public class Mail extends InputStream {
    private String mailTo;
    private String subject;
    private String message;

    public Mail(String mailTo, String subject, String message) {
        this.mailTo = mailTo;
        this.subject = subject;
        this.message = message;
    }

    @Override
    public int read() throws IOException {
        return 0;
    }
}
