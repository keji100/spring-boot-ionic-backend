package com.willianrosa.cursomc.resources.exception;

import java.io.Serializable;

public class FieldMessage implements Serializable{
	private static final long serialVersionUID = 1L;

	private String fieldname;
	private String message;
	
	public FieldMessage() {}

	public FieldMessage(String fieldname, String message) {
		this.fieldname = fieldname;
		this.message = message;
	}

	public String getFieldName() {
		return fieldname;
	}

	public void setFieldName(String fieldname) {
		this.fieldname = fieldname;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
