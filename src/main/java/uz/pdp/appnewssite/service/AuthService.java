package uz.pdp.appnewssite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.appnewssite.entity.User;
import uz.pdp.appnewssite.exceptions.ResourceNotFoundException;
import uz.pdp.appnewssite.payload.ApiResponse;
import uz.pdp.appnewssite.payload.RegisterDto;
import uz.pdp.appnewssite.repository.LavozimRepository;
import uz.pdp.appnewssite.repository.UserRepository;
import uz.pdp.appnewssite.utils.AppConstants;

@Service
public class AuthService implements UserDetailsService {
    @Autowired
    LavozimRepository lavozimRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserRepository userRepository;

    public ApiResponse registerUser(RegisterDto dto) {
        if (!dto.getPassword().equals(dto.getPrePassword())){
            return new ApiResponse("Parollar mos emas",false);
        }
        if (userRepository.existsByUsername(dto.getUsername())){
            return new ApiResponse("Bunday user ro'yxatdan o'tgan",false);
        }
        User user = new User(
                dto.getFullName(),
                dto.getUsername(),
                passwordEncoder.encode(dto.getPassword()),
                lavozimRepository.findByName(AppConstants.USER).orElseThrow(() ->new ResourceNotFoundException("lavozim","name",AppConstants.USER)),
                true
        );
        userRepository.save(user);
        return new ApiResponse("Muvaffaqiyatlik Ro'hatdan o'tdingiz",true);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() ->new UsernameNotFoundException(username));
    }
}
