package com.example.claimsmicroservice.services;

import com.example.claimsmicroservice.dto.SmsRequest;
import com.example.claimsmicroservice.entities.Claim;
import com.example.claimsmicroservice.entities.Status;
import com.example.claimsmicroservice.entities.TypeReclamation;


import java.util.Date;
import java.util.List;

public interface IServiceClaim {
     Claim addClaim (Claim claim);
     List<Claim> findAllClaims();
     Claim findClaimById(Long id);
     //Claim changeStatus(Long id,Claim claimRequest);
     Claim changeStatus(Claim claim);
     void cancelClaim(Long id);
     void removeClaim(Long id);
     Integer nbClaimsResolu(Date dateDiff, Date dateRes);
     Claim resolveClaim(Claim claim);
     //Claim returnClaim(Long id,Claim claim);
     Claim returnClaim(Claim claim);
     //Claim archiveClaim(Long id);
     Claim archiveClaim(Claim claim);
     Claim editClaim(Claim claim);
     List<Claim> findReclamationByStatus();
     List<Claim> findByDateDiff(Date startDate, Date endDate);
     List<Claim> findByDateRes(Date startDate, Date endDate);

     List<Claim> findClaimsByTypeReclamation(TypeReclamation type);
     List<Claim> search(String keywords);
     String Noticate();
     List<Claim> findByUsername(String username);
     void banUserToAddClaim(String username,SmsRequest smsRequest);
     void deleteClaimByStatusResolu();
     long getDaysBetween(Long id);
     List<Claim> findClaimsByStatus(Status status);
     List<Claim> findHistory();









}