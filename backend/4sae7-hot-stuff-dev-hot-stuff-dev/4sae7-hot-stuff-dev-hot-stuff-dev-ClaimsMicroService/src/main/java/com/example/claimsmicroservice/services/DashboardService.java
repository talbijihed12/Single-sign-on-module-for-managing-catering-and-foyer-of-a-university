package com.example.claimsmicroservice.services;

import com.example.claimsmicroservice.entities.Claim;
import com.example.claimsmicroservice.entities.Status;
import com.example.claimsmicroservice.entities.TypeReclamation;
import com.example.claimsmicroservice.repositories.ClaimRepository;
import com.example.claimsmicroservice.responses.BarResponse;
import com.example.claimsmicroservice.responses.PieResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class DashboardService {

    private ClaimRepository claimRepository;


    /*public PieResponse pieClaim(){


        PieResponse pieResponse = new PieResponse();
        List<String> labels = new ArrayList<>();
        labels.add("In progress");
        labels.add("Resolve");
        labels.add("Archived");
        labels.add("Returned");
        labels.add("Modified");
        List<Long> values = new ArrayList<>();
        values.add(claimRepository.countByStatus(Status.EN_COURS));
        values.add(claimRepository.countByStatus(Status.RESOLU));
        values.add(claimRepository.countByStatus(Status.ARCHIVER));
        values.add(claimRepository.countByStatus(Status.RETOUR));
        values.add(claimRepository.countByStatus(Status.MODIFIER));
        pieResponse.setValues(values);
        pieResponse.setLabels(labels);
        return  pieResponse;


    }*/


    public PieResponse pieReclamation(){


        PieResponse pieResponse = new PieResponse();
        List<String> labels = new ArrayList<>();
        labels.add("In Progress");

        labels.add("Resolved");
        labels.add("Archived");
        labels.add("Returned");
        labels.add("Modified");
        List<Long> values = new ArrayList<>();
        values.add(claimRepository.countByStatus(Status.EN_COURS));
        values.add(claimRepository.countByStatus(Status.RESOLU));
        values.add(claimRepository.countByStatus(Status.ARCHIVER));
        values.add(claimRepository.countByStatus(Status.RETOUR));
        values.add(claimRepository.countByStatus(Status.MODIFIER));
        pieResponse.setValues(values);
        pieResponse.setLabels(labels);
        return  pieResponse;


    }


    public BarResponse getClaims(){
        BarResponse barResponse = new BarResponse();
        List<String> labels = new ArrayList<>();
        labels.add("January");
        labels.add("February");
        labels.add("March");
        labels.add("April");
        labels.add("May");
        labels.add("June");
        labels.add("July");
        labels.add("August");
        labels.add("September");
        labels.add("October");
        labels.add("November");
        labels.add("December");
        barResponse.setLabels(labels);

        List<Long> bar1 = new ArrayList<>();
        List<Long> line1 = new ArrayList<>();
        for(int i =1; i<=12; i++) {
            bar1.add(claimRepository.countAllByMonth(i));
            line1.add(claimRepository.countResoluByMonth(i));

        }
        barResponse.setBar1(bar1);
        barResponse.setLine1(line1);

        return barResponse;


    }


    public PieResponse pieClaimByStatusAndUsername(String username){
        Claim claim= new Claim();
        claim.setUsername(username);

        PieResponse pieResponse = new PieResponse();
        List<String> labels = new ArrayList<>();
        labels.add("In progress");
        labels.add("Resolve");
        labels.add("Archived");
        labels.add("Returned");
        labels.add("Modified");
        List<Long> values = new ArrayList<>();
        values.add(claimRepository.countByStatusAndUsername(Status.EN_COURS,username));
        values.add(claimRepository.countByStatusAndUsername(Status.RESOLU,username));
        values.add(claimRepository.countByStatusAndUsername(Status.ARCHIVER,username));
        values.add(claimRepository.countByStatusAndUsername(Status.RETOUR,username));
        values.add(claimRepository.countByStatusAndUsername(Status.MODIFIER,username));
        pieResponse.setValues(values);
        pieResponse.setLabels(labels);
        return  pieResponse;


    }


    public PieResponse getClaimsByType(){
        PieResponse pieResponse = new PieResponse();
        List<String> labels = new ArrayList<>();
        labels.add("RESTAURANT");
        labels.add("ACADEMIC_HOME");
        labels.add("SUBSCRIPTION");
        labels.add("FORUM");
        labels.add("RESERVATION");

        pieResponse.setLabels(labels);

        List<Long> values = new ArrayList<>();

        values.add(claimRepository.countClaimsByTypeReclamation(TypeReclamation.RESTAURANT));
        values.add(claimRepository.countClaimsByTypeReclamation(TypeReclamation.ACADEMIC_HOME));
        values.add(claimRepository.countClaimsByTypeReclamation(TypeReclamation.SUBSCRIPTION));
        values.add(claimRepository.countClaimsByTypeReclamation(TypeReclamation.FORUM));
        values.add(claimRepository.countClaimsByTypeReclamation(TypeReclamation.RESERVATION));


        pieResponse.setValues(values);

        return pieResponse;


    }

}