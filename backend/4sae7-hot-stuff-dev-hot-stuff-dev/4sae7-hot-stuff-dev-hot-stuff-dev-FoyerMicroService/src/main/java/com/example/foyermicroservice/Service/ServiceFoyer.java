package com.example.foyermicroservice.Service;

import com.example.foyermicroservice.Entity.Block;
import com.example.foyermicroservice.Repository.BlockRepository;
import com.example.foyermicroservice.Repository.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ServiceFoyer implements IServiceFoyer{
    private RoomRepository roomRepo;
    private BlockRepository blockRepo ;
    @Override
    public Block ajouterBlock(Block block){
        return blockRepo.save(block);

    }



    @Override
    public void deleteBlock( long idB) {
        Block block = blockRepo.getReferenceById(idB);
        blockRepo.delete(block);
    }
    @Override
    public Block updateBlock(Block block, long idB){
        Block block1 = blockRepo.findById(idB).orElse(null);
        block1.setSexe(block.getSexe());
        block1.setType(block.getType());
        blockRepo.save(block1);
        return block1;

    }
    @Override
    public List<Block> getBlocks(){

        return (List<Block>) blockRepo.findAll();
    }

//////////////////////////////////////












    ////////////////////////

}


