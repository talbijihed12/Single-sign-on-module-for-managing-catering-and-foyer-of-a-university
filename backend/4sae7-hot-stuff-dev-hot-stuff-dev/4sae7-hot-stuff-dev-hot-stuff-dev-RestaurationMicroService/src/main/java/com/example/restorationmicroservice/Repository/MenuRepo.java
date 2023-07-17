package com.example.restorationmicroservice.Repository;

import com.example.restorationmicroservice.Entity.Menu;
import com.example.restorationmicroservice.Entity.TypeEmplacement;
import com.example.restorationmicroservice.Entity.TypeMenu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepo  extends JpaRepository<Menu,Long> {
    Menu findMenuByTypeMenuAndTypeEmplacement(TypeMenu typeMenu, TypeEmplacement typeEmplacement);
    Menu findMenuByTypeMenu(TypeMenu typeMenu);
}
