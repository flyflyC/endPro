package com.vedu.service;

import java.util.Map;

public interface MsmService {
    Boolean send(Map<String, Object> param, String phone);
}
