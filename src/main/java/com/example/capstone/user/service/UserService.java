package com.example.capstone.user.service;

import com.example.capstone.user.entity.AppUser;
import com.example.capstone.user.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final AppUserRepository repo;

    @Transactional
    public AppUser upsertGoogleUser(String sub, String email, String name, String pictureUrl) {
        return repo.findByGoogleSub(sub).map(u -> {
            u.updateFromGoogle(email, name, pictureUrl);
            return u;
        }).orElseGet(() -> repo.save(
                AppUser.builder()
                        .googleSub(sub)
                        .email(email)
                        .name(name)
                        .pictureUrl(pictureUrl)
                        .build()
        ));
    }

    @Transactional
    public AppUser updateWalletAddress(Long userId, String walletAddress) {
        AppUser user = repo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        user.updateWalletAddress(walletAddress);
        return user;
    }
}
