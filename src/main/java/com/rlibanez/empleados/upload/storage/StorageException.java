package com.rlibanez.empleados.upload.storage;

public class StorageException extends RuntimeException {

	private static final long serialVersionUID = 292685131115605978L;

	public StorageException(String message) {
		super(message);
	}

	public StorageException(String message, Throwable cause) {
		super(message, cause);
	}

}
