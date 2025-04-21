package com.microservices.mark.business;

import com.microservices.mark.dataaccess.entity.UserPermission;
import java.util.List;
import java.util.Optional;

public interface QueryUserService {

    Optional<List<UserPermission>> findAllPermissionsByUsername(String username);
}
