package com.thoughtworks.springbootemployee.exception;

public class CompanyNameExistedException extends Exception {

    public CompanyNameExistedException() {
        super("name: company name existed");
    }
}
