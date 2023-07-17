package com.example.restorationmicroservice.Services;

import com.example.restorationmicroservice.EmailService.EmailService;
import com.example.restorationmicroservice.Entity.Menu;
import com.example.restorationmicroservice.Entity.TypeMenu;
import com.example.restorationmicroservice.Repository.MenuRepo;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor

public class MenuImpl implements IService<Menu>,IMenuService{

    private MenuRepo menuRepo ;
    private EmailService emailService;

    @Override
    public Menu Create(Menu menu) {
        return menuRepo.save(menu);
    }

    @Override
    public List<Menu> Read() {
        return menuRepo.findAll();
    }

    @Override
    public Menu Update( Long ID ,Menu T) {
        return menuRepo.save(T);
    }

    @Override
    public void Delete(Long ID) {
        menuRepo.deleteById(ID);
    }

    @Override
    public Menu getOne(Long ID) {
        return menuRepo.findById(ID).orElse(null);
    }

    @Override
    public Menu findMenuByTypeMenu(TypeMenu typeMenu) {

        return menuRepo.findMenuByTypeMenu(TypeMenu.Personnalisé);
    }

    public Menu findMenuByTypeMenu2(TypeMenu typeMenu) {

        return menuRepo.findMenuByTypeMenu(TypeMenu.Standard);
    }
}
