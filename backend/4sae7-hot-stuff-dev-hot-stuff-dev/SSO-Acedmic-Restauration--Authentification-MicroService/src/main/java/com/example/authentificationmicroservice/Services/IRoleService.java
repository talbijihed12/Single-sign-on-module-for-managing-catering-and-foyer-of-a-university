package com.example.authentificationmicroservice.Services;

import com.example.authentificationmicroservice.Entity.Role;
import com.example.authentificationmicroservice.Entity.RoleEnum;

import java.util.List;

public interface IRoleService {
    Role addRole(RoleEnum roleEnum);

    boolean roleExist(RoleEnum roleEnum);

    List<Role> listRole();

    List<Role> listRoleByRoleEnumsList(List<RoleEnum> roleEnums);

    Role findRoleByRoleEnum(RoleEnum roleEnum);
}
