package com.phyous.techsense.model;

import org.immutables.value.Value;

@Value.Immutable
public interface Tweet {
    
    String username();
    String handle();
    String text();
    String url();
}
