package com.example.registries;

import io.micrometer.core.instrument.ImmutableTag;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.Tag;
import java.util.*;


public class CustomSimpleRegistry {

    private  MeterRegistry smr ;

    public CustomSimpleRegistry(MeterRegistry mr) {
        this.smr=mr;
        Metrics.addRegistry(mr);
    }

    public void IncrementCounter(String name,String tag,String value){
        smr.counter(name,tag,value).increment();
    }

    public void AddCounter(String name, String tag,String value) {
        smr.counter(name, tag,value);
    }

    public void AddCounter(String name){
        smr.counter(name);
    }

    public void AddCounter(String name, HashMap<String, String> tags) {

        final Set<Map.Entry<String, String>> tagsMap = tags.entrySet();

        if(tags ==null || tags.size()==0)
            throw new IllegalArgumentException("A HashMap of tags for Micrometer cannot be empty or null");

        Iterable<Tag> actualTags = new Iterable<Tag>() {
            public Iterator<Tag> iterator() {
                List<Tag> finalTags = new ArrayList<Tag>(tagsMap.size());
                Iterator<Map.Entry<String, String>> ie = tagsMap.iterator();
                Map.Entry<String, String> e;
                while (ie.hasNext()) {
                    e = ie.next();
                    finalTags.add(new ImmutableTag(e.getKey(), e.getValue()));
                }
                return finalTags.iterator();
            }
        };

        smr.counter(name,actualTags);
    }

    public void AddTimer(String name, String tag) {
        smr.timer(name, tag);
    }

    public void AddGauge(String name, int tag) {
        smr.gauge(name, tag);
    }

}
