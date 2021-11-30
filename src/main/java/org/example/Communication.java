package org.example;

import org.example.controller.RestControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Communication {
    private RestControl restControl;

    @Autowired
    public Communication(RestControl restControl) {
        this.restControl = restControl;
    }

    public String getAnswer() {
        return restControl.saveUser() + restControl.updateUser() + restControl.deleteUser(3L);
    }
}
