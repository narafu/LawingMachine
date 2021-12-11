package com.web.lawingmachine.app.training.container;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TrainingController {

    @GetMapping("/training/exam")
    public String exam() {
        return "view/training/exam";
    }



}
