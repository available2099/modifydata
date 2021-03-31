package com.demo.ai.util;

import java.util.Map;

public class GlobalConstants {
    public     static ThreadLocal<Map<String, String>> threadLocalUser = new ThreadLocal<>();

}
