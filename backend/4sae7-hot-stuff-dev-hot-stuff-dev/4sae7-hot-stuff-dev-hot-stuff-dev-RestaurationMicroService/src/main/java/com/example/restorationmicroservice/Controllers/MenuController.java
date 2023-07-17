package com.example.restorationmicroservice.Controllers;


import com.example.restorationmicroservice.Entity.Menu;
import com.example.restorationmicroservice.Entity.TypeMenu;
import com.example.restorationmicroservice.Services.IMealService;
import com.example.restorationmicroservice.Services.IMenuService;
import com.example.restorationmicroservice.Services.IService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
public class MenuController {
    private IService<Menu> iService;
    private IMenuService iMenuService;
   // @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_RESTEAU','PERSONNEL','PROFESSEUR','ETUDIANT')")
    @GetMapping("/getMenu")
    public List<Menu> Read()
    {
        return iService.Read();
    }

   // @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_RESTEAU','PERSONNEL','PROFESSEUR','ETUDIANT')")
    @GetMapping("/getOnemenu/{idmenu}")
    public Menu getOnemenu(@PathVariable("idmenu") Long ID)
    {
        return iService.getOne(ID);
    }

   // @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_RESTEAU')")
    @PostMapping("/ajouterMenu")
    Menu ajouterMenu(@RequestBody Menu menu) {
        return iService.Create(menu);
    }

    //@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_RESTEAU')")
    @PutMapping("/updateMenu/{idmenu}")
    public Menu updateMenu(@PathVariable("idmenu") Long ID,@RequestBody Menu menu)
    {
        return iService.Update(ID,menu);
    }

    //@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_RESTEAU')")
    @DeleteMapping("/deletemenu/{ID}")
    public void deletemenu(@PathVariable Long ID)
    {
        iService.Delete(ID);
    }

  //  @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_RESTEAU')")
    @GetMapping("/findMenuByTypeMenu")
    public Menu findMenuByTypeMenu(TypeMenu typeMenu){
        return iMenuService.findMenuByTypeMenu(typeMenu);
    }




}
