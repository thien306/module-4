package com.codegym.utils;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class WebUtils {

    public static String toString(User user) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("UserName:").append(user.getUsername());

        Collection<GrantedAuthority> authorities = user.getAuthorities();

        if (authorities != null && !authorities.isEmpty()) {
            stringBuilder.append("(");

            boolean first = true;
            for (GrantedAuthority authority : authorities) {
                if (first) {
                    stringBuilder.append(authority.getAuthority());
                    first = false;
                } else {
                    stringBuilder.append(", ").append(authority.getAuthority());
                }
            }
            stringBuilder.append(")");
        }
        return stringBuilder.toString();
    }
}

