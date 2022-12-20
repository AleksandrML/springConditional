package com.example.springconditional.configs;

import com.example.springconditional.interfaces.SystemProfile;

public class ProductionProfile implements SystemProfile {

    @Override
    public String getProfile() {
        return "Current profile is production";
    }

}
