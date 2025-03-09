package com.toki.backend.auth.repository;

import com.toki.backend.auth.entity.RefreshToken;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;
@Repository
public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {


    int countByUserPk(String userTag);

    int countByRefreshToken(String userPk);


}
