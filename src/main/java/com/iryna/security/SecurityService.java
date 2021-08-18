package com.iryna.security;

import com.iryna.entity.Product;
import com.iryna.entity.Role;
import com.iryna.entity.User;
import com.iryna.loader.SettingsLoader;
import com.iryna.service.UserService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class SecurityService {

    private UserService userService;
    private PasswordEncryptor passwordEncryptor;
    private SettingsLoader settingsLoader;

    private HashMap<String, Session> sessionList = new HashMap();

    public List<Product> getChartByToken(String token) {
        return sessionList.get(token).getCart();
    }

    public Session getSession(String token) {
        if (token != null) {
            Session session = sessionList.get(token);
            if (isTokenValid(token)) {
                return session;
            }
        }
        return null;
    }

    public boolean isAccessAllowForRole(Role role, String token) {
        Session session = sessionList.get(token);
        return session.getUser().getRole().equals(role);
    }

    public boolean isTokenValid(String token) {
        Session session = sessionList.get(token);
        if (session == null) {
            return false;
        }
        if (session.getExpireDate().isBefore(LocalDateTime.now())) {
            sessionList.remove(token);
            return false;
        }
        return true;
//        return sessionList.get(token) != null;
    }

    public String login(String name, String password) {
        if (checkPassword(name, password)) {
            return generateAndRegisterToken(userService.getUserByName(name));
        }
        return null;
    }

    public void logout(String token) {
        sessionList.remove(token);
    }

    private boolean checkPassword(String name, String password) {
        User user = userService.getUserByName(name);
        return passwordEncryptor.encryptPassword(password, user.getGeneratedSalt()).equals(user.getPassword());
    }

    private String generateAndRegisterToken(User user) {
        String uuid = UUID.randomUUID().toString();
        Session session = new Session(LocalDateTime.now().plusHours(settingsLoader.getTimeToLiveSession() / 3600), new ArrayList<>(), user);
        sessionList.put(uuid, session);
        return uuid;
    }

    public void setSettingsLoader(SettingsLoader settingsLoader) {
        this.settingsLoader = settingsLoader;
    }

    public void setPasswordEncryptor(PasswordEncryptor passwordEncryptor) {
        this.passwordEncryptor = passwordEncryptor;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}