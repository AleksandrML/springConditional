package com.example.springconditional.configs;

import com.example.springconditional.interfaces.SystemProfile;

public class DevProfile implements SystemProfile {

    @Override
    public String getProfile() {
        return "Current profile is dev";
    }

}
