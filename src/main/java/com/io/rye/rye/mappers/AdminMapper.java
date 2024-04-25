package com.io.rye.rye.mappers;

import com.io.rye.rye.dto.AdminDto;
import com.io.rye.rye.entity.Admin;

public class AdminMapper {

    public static Admin fromDto(AdminDto adminDto) {
        Admin admin = new Admin();
        admin.setId(adminDto.getId());
        admin.setPassword(admin.getPassword());
        admin.setLogin(adminDto.getLogin());

        return admin;
    }

    public static AdminDto toDto(Admin admin) {
        AdminDto adminDto = new AdminDto();
        adminDto.setId(admin.getId());
        adminDto.setLogin(admin.getLogin());
        adminDto.setPassword(admin.getPassword());

        return adminDto;
    }

}
