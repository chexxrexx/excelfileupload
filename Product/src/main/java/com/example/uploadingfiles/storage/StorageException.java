package com.example.uploadingfiles.storage;

public class StorageException extends RuntimeException {

	public StorageException(String message) {
		super(message); // error message
	}

	public StorageException(String message, Throwable cause) {
		super(message, cause); // error message and cause
	}
}
