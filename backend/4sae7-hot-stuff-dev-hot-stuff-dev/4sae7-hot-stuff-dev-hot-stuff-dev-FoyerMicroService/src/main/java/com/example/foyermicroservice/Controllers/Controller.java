package com.example.foyermicroservice.Controllers;

import com.example.foyermicroservice.Entity.Block;
import com.example.foyermicroservice.Entity.Room;
import com.example.foyermicroservice.Entity.Sexe;
import com.example.foyermicroservice.Entity.Types;
import com.example.foyermicroservice.Service.IServiceFoyer;
import com.example.foyermicroservice.Service.IServiceRoom;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class Controller {
    private IServiceFoyer iServiceFoyer;
    private IServiceRoom iServiceRoom ;
    //@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_FOYER')")
    @PostMapping("/ajouterBlock")
    public Block ajouterBlock(@RequestBody Block block){
        return iServiceFoyer.ajouterBlock(block);
    }
   // @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_FOYER')")
    @GetMapping("/Blocks")
    public List<Block> getBlocks() {
        return (List<Block>) iServiceFoyer.getBlocks();
    }


    //@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_FOYER')")
    @DeleteMapping("/deleteBlock/{idB}")
    public void deleteBlock(@PathVariable long idB){
        iServiceFoyer.deleteBlock(idB);

    }
  //  @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_FOYER')")
    @PutMapping("/updateBlock/{idB}")
    public Block updateBlock(@RequestBody Block block , @PathVariable long idB){
        return iServiceFoyer.updateBlock(block,idB);
    }
//////////////////////////////////




}