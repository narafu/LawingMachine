package com.web.lawingmachine.app.common.controller;

import com.web.lawingmachine.app.common.vo.ModalVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller()
@RequestMapping("/modal")
public class ModalController {

    @RequestMapping("/alert")
    public String alert(ModalVO modalVO, ModelMap model) {
        model.addAttribute("modalVO", modalVO);
        return "view/modal/alert";
    }
    
    @RequestMapping("/confirm")
    public String confirm(ModalVO modalVO, ModelMap model) {
    	model.addAttribute("modalVO", modalVO);
    	return "view/modal/confirm";
    }

    @RequestMapping("/dialog")
    public String dialog(ModalVO modalVO, ModelMap model) {
        model.addAttribute("modalVO", modalVO);
        return "view/modal/dialog";
    }

    @RequestMapping("/quizStartConfirm")
    public String quizStartConfirm(ModalVO modalVO, ModelMap model) {
        model.addAttribute("modalVO", modalVO);
        return "view/modal/quizStartConfirm";
    }

    @RequestMapping("/quizResultConfirm")
    public String quizResultConfirm(ModalVO modalVO, ModelMap model) {
        model.addAttribute("modalVO", modalVO);
        return "view/modal/quizResultConfirm";
    }

}
