package com.phyous.techsense.model;

import com.sun.istack.internal.Nullable;
import org.immutables.value.Value;

import java.util.List;

@Value.Immutable
public interface Headline {
    
    String reporter();
    String source();
    @Nullable String title();
    @Nullable String summary();
    @Nullable List<Headline> relatedHeadlines();
    @Nullable List<Tweet> tweets();
}
