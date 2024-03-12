package com.ecommercetc5.loginusers.user.request;

import com.ecommercetc5.loginusers.user.enums.UserRole;

public record UserRequest(String login, String password, UserRole role) {
}
