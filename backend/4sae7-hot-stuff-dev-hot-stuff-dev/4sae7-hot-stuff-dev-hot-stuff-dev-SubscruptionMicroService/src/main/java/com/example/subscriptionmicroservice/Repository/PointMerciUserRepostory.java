package com.example.subscriptionmicroservice.Repository;

import com.example.subscriptionmicroservice.Entity.Abonnement_F;
import com.example.subscriptionmicroservice.Entity.Abonnement_R;
import com.example.subscriptionmicroservice.Entity.PointMerciUser;
import com.example.subscriptionmicroservice.Entity.TypeFoyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PointMerciUserRepostory extends JpaRepository<PointMerciUser, Long> {


  PointMerciUser findByUsername(String username);


}
