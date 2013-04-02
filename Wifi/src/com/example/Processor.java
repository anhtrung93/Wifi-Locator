// @author: bvuong1993

// interface Processor
// this is the base of classes which every specific request handler will implement
// has only one method Object process(Object)

package com.example;

public interface Processor {
    public Object process(Object obj);
}
