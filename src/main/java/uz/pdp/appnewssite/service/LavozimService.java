package uz.pdp.appnewssite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appnewssite.entity.Lavozim;
import uz.pdp.appnewssite.payload.ApiResponse;
import uz.pdp.appnewssite.payload.LavozimDto;
import uz.pdp.appnewssite.repository.LavozimRepository;

@Service
public class LavozimService {
    @Autowired
    LavozimRepository lavozimRepository;

    public ApiResponse addLavozim(LavozimDto dto) {
        if (lavozimRepository.existsByName(dto.getName())){
            return new ApiResponse("Bunday Lavozim bor",false);
        }
        Lavozim lavozim = new Lavozim(
                dto.getName(),
                dto.getHuquqList(),
                dto.getDescription()
        );
        lavozimRepository.save(lavozim);
        return new ApiResponse("Saqlandi",true);
    }
}
