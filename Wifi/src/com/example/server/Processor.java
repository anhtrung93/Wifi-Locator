package com.example.server;

/**
 * 
 * @author bvuong93
 * 
 *         Interface Processor is base of classes which every specific request
 *         handler will implement. This has only one method Object
 *         process(Object)
 * 
 */
public interface Processor {
	public Object process(Object obj);
}
