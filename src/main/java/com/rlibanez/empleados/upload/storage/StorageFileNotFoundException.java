package com.rlibanez.empleados.upload.storage;

public class StorageFileNotFoundException extends StorageException {

	private static final long serialVersionUID = -5390477529721075358L;

	public StorageFileNotFoundException(String message) {
		super(message);
	}

	public StorageFileNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

}
