package com.example.toy.restfull.services.impl;

import com.example.toy.restfull.services.TempService;
import org.springframework.stereotype.Service;

@Service("tempService")
public class TempServiceImpl implements TempService {

    @Override
    public String getTempMessage() {
        return "tempMethod Success";
    }

}
