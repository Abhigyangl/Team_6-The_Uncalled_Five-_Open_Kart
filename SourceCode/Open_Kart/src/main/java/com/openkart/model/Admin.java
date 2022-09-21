package com.openkart.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.logging.log4j.message.AsynchronouslyFormattable;

import lombok.Data;
@Data
@Entity
@Table(name="Admin")
public class Admin 
{
	@Id
	private int adminId;
    private String adminPassword;

}
