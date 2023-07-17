package com.example.restorationmicroservice.Services;

import com.example.restorationmicroservice.Entity.Menu;
import com.example.restorationmicroservice.Entity.TypeMenu;

public interface IMenuService {
    Menu findMenuByTypeMenu(TypeMenu typeMenu );
}
