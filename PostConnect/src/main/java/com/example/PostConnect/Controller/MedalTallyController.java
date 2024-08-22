package com.example.PostConnect.Controller;

import com.example.PostConnect.Repository.MedalTallyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.PostConnect.Models.MedalTally;
import java.util.List;
@RestController
public class MedalTallyController {
    @Autowired
    MedalTallyRepository medalTallyRepository;

    @GetMapping("/olympics/medaltally")
    public List<MedalTally> getMedalTally() {
        List<MedalTally> result=medalTallyRepository.findAll();
        System.out.println("Fetched MedalTally data: " + result);
        return result;
    }
}
