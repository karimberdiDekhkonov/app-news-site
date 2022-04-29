package uz.pdp.appnewssite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.appnewssite.payload.ApiResponse;
import uz.pdp.appnewssite.payload.LavozimDto;
import uz.pdp.appnewssite.payload.RegisterDto;
import uz.pdp.appnewssite.service.AuthService;
import uz.pdp.appnewssite.service.LavozimService;

@RestController
@RequestMapping("/api/lavozim")
public class LavozimController {
    @Autowired
    LavozimService lavozimService;

    @PreAuthorize(value = "hasAuthority('ADD_USER')")
    @PostMapping
    HttpEntity<?> addLavozim(@RequestBody LavozimDto dto){
        ApiResponse apiResponse = lavozimService.addLavozim(dto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }
}
