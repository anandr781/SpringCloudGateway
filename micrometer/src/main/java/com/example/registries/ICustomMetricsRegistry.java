package com.example.registries;

import java.util.HashMap;

public interface ICustomMetricsRegistry {
    void IncrementCounter(String name, String tag, String value);

    void AddCounter(String name, String tag, String value);

    void AddCounter(String name);

    void AddCounter(String name, HashMap<String, String> tags);

    void AddTimer(String name, String tag);

    void AddGauge(String name, int tag);
}
