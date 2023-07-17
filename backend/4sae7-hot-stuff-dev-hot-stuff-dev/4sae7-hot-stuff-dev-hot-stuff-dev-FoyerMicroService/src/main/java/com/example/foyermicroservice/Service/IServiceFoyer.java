package com.example.foyermicroservice.Service;

import com.example.foyermicroservice.Entity.Block;
import com.example.foyermicroservice.Entity.Room;
import com.example.foyermicroservice.Entity.Sexe;
import com.example.foyermicroservice.Entity.Types;

import java.util.List;

public interface IServiceFoyer {
    public Block ajouterBlock(Block block);

    Block updateBlock(Block block, long idB);

    public List<Block> getBlocks() ;


    public void deleteBlock( long idB) ;




}
