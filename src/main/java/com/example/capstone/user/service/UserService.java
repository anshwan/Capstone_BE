package com.example.capstone.user.service;

import com.example.capstone.user.entity.AppUser;
import com.example.capstone.user.repository.AppUserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private final AppUserRepository repo;

    public UserService(AppUserRepository repo) {
        this.repo = repo;
    }

    @Transactional
    public AppUser upsertGoogleUser(String sub, String email, String name, String pictureUrl) {
        return repo.findByGoogleSub(sub).map(u -> {
            u.updateFromGoogle(email, name, pictureUrl);
            return u;
        }).orElseGet(() -> repo.save(
           AppUser.builder().googleSub(sub).email(email).name(name).pictureUrl(pictureUrl).build()
        ));
    }
}
