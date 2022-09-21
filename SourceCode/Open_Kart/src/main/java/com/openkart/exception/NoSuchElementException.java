package com.openkart.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

//Element doesn't Exist exception
public class NoSuchElementException extends RuntimeException 
{
   private static final long serialVersionUID = 1L;
   private String msg;
   private int id;
   
}