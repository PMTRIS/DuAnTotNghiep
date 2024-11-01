package com.mt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiService {

    @Autowired
    private RestTemplate restTemplate;

    public String getCityNameById(String cityId) {
        String url = String.format("https://vapi.vnappmob.com/api/province/%s", cityId);
        return restTemplate.getForObject(url, String.class);
    }

    public String getDistrictNameById(String cityId, String districtId) {
        String url = String.format("https://vapi.vnappmob.com/api/province/district", districtId);
        return restTemplate.getForObject(url, String.class);
    }

    public String getWardNameById(String districtId, String wardId) {
        String url = String.format("https://vapi.vnappmob.com/api/province/ward/%s", wardId);
        return restTemplate.getForObject(url, String.class);
    }

}
