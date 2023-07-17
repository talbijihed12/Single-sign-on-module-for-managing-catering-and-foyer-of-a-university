package com.example.authentificationmicroservice.Services;

import com.example.authentificationmicroservice.Entity.NotificationMail;
import com.example.authentificationmicroservice.Entity.SexeEnum;
import com.example.authentificationmicroservice.Entity.User;
import com.example.authentificationmicroservice.Entity.VerificationToken;
import com.example.authentificationmicroservice.Payload.Request.KeycloakUser;
import com.example.authentificationmicroservice.Repositories.VerificationTokenRepo;
import com.example.authentificationmicroservice.config.Credentials;
import com.example.authentificationmicroservice.config.KeycloakConfig;
import com.example.authentificationmicroservice.utils.exceptions.BadRequestException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.RoleMappingResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import javax.ws.rs.core.Response;
import java.util.*;

import static com.example.authentificationmicroservice.config.Credentials.createPasswordCredentials;


@Service
@AllArgsConstructor
@Slf4j
public class KeyCloakService {

    private Keycloak keycloak;
    private VerificationTokenRepo verificationTokenRepo;
    private MailService mailService;
    private final Logger logger = LoggerFactory.getLogger(getClass());


    public UserRepresentation addUser(KeycloakUser keycloakUser) {
        UsersResource usersResource = keycloak.realm(KeycloakConfig.realm).users();

        CredentialRepresentation credentialRepresentation = Credentials
                .createPasswordCredentials(keycloakUser.getPassword());
        UserRepresentation kcUser = new UserRepresentation();
        kcUser.setUsername(keycloakUser.getUsername());
        kcUser.setCredentials(Collections.singletonList(credentialRepresentation));
        kcUser.setFirstName(keycloakUser.getFirstName());
        kcUser.setLastName(keycloakUser.getLastName());
        kcUser.setEmail(keycloakUser.getEmail());
        kcUser.setEnabled(true);
        kcUser.setEmailVerified(false);
        kcUser.singleAttribute("soldeJeton", Long.toString(keycloakUser.getSoldeJeton()));
        kcUser.singleAttribute("points", Long.toString(keycloakUser.getPoints()));
        String enumValueAsString = keycloakUser.getGender().toString();
        kcUser.singleAttribute("gender", enumValueAsString);
        if (usersResource.search(keycloakUser.getUsername()).size() > 0) {
            throw new BadRequestException("User already Exist");
        }
        UsersResource instance = getInstance();
        instance.create(kcUser);

        return kcUser;
    }

    public UserRepresentation addUserByAdmin(KeycloakUser keycloakUser) {
        RealmResource realmResource = keycloak.realm(KeycloakConfig.realm);
        UsersResource usersResource = keycloak.realm(KeycloakConfig.realm).users();
        CredentialRepresentation credentialRepresentation = Credentials
                .createPasswordCredentials(keycloakUser.getPassword());
        UserRepresentation kcUser = new UserRepresentation();
        kcUser.setUsername(keycloakUser.getUsername());
        kcUser.setCredentials(Collections.singletonList(credentialRepresentation));
        kcUser.setFirstName(keycloakUser.getFirstName());
        kcUser.setLastName(keycloakUser.getLastName());
        kcUser.setEmail(keycloakUser.getEmail());
        kcUser.setEnabled(true);
        kcUser.setEmailVerified(false);
        kcUser.singleAttribute("soldeJeton", Long.toString(keycloakUser.getSoldeJeton()));
        kcUser.singleAttribute("points", Long.toString(keycloakUser.getPoints()));
        String enumValueAsString = keycloakUser.getGender().toString();
        kcUser.singleAttribute("gender", enumValueAsString);
        if (usersResource.search(keycloakUser.getUsername()).size() > 0) {
            throw new BadRequestException("User already Exist");
        }





        UsersResource instance = getInstance();
        instance.create(kcUser);
        return kcUser;
    }

    public UsersResource getInstance() {
        return KeycloakConfig.getInstance().realm(KeycloakConfig.realm).users();
    }


    public UserRepresentation getUser(String userId) {
        UserRepresentation user = keycloak.realm("SSO-Acedmic-Restauration")
                .users().get(userId).toRepresentation();
        return user;

    }
    public UserRepresentation getUserByName(String username) {
        UserRepresentation user = keycloak.realm("SSO-Acedmic-Restauration")
                .users().search(username).stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("User not found"));
        return user;

    }
   // @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<UserRepresentation> getUsers() {
        UsersResource usersResource = getInstance();
        List<UserRepresentation> user = usersResource.list();
        return user;

    }

    public void updateUserByAmin(String userId, KeycloakUser keycloakUser) {
        CredentialRepresentation credential = createPasswordCredentials(keycloakUser.getPassword());
        UserRepresentation user = new UserRepresentation();
        user.setUsername(keycloakUser.getUsername());
        user.setFirstName(keycloakUser.getFirstName());
        user.setLastName(keycloakUser.getLastName());
        user.setEmail(keycloakUser.getEmail());
        user.setCredentials(Collections.singletonList(credential));

        UsersResource usersResource = getInstance();
        usersResource.get(userId).update(user);
    }

    public void updateUser(String userId, KeycloakUser keycloakUser) {
        CredentialRepresentation credential = createPasswordCredentials(keycloakUser.getPassword());
        UserRepresentation user = new UserRepresentation();
        user.setUsername(keycloakUser.getUsername());
        user.setFirstName(keycloakUser.getFirstName());
        user.setLastName(keycloakUser.getLastName());
        user.setEmail(keycloakUser.getEmail());
        user.setCredentials(Collections.singletonList(credential));

        UsersResource usersResource = getInstance();
        usersResource.get(userId).update(user);
    }


    public void deleteUser(String userId) {
        UsersResource usersResource = getInstance();
        usersResource.get(userId)
                .remove();
    }
    public void deleteMyAccount(String userId) {
        UsersResource usersResource = getInstance();
        usersResource.get(userId)
                .remove();
    }


    public void assignRealmRolesToUser(KeycloakUser keycloakUser, String userName) {
        UsersResource usersResource = keycloak.realm(KeycloakConfig.realm).users();
        UserRepresentation user = usersResource.search(userName).get(0);
         usersResource = keycloak.realm(KeycloakConfig.realm).users();
         user = usersResource.search(userName).get(0);
        RoleRepresentation role = keycloak.realm(KeycloakConfig.realm).roles().get(keycloakUser.getRole()).toRepresentation();
        RoleMappingResource roleMappingResource = usersResource.get(user.getId()).roles();
        roleMappingResource.realmLevel().add(Collections.singletonList(role));

        List<RoleRepresentation> assignedRoles = roleMappingResource.realmLevel().listEffective();
        boolean roleAssigned = assignedRoles.stream()
                .map(RoleRepresentation::getName)
                .anyMatch(keycloakUser.getRole()::equals);
        if (!roleAssigned) {
            throw new RuntimeException("Failed to assign role to user.");
        }

    }


/*
        RealmResource realmResource = keycloak.realm(KeycloakConfig.realm);
        UsersResource usersResource = keycloak.realm(KeycloakConfig.realm).users();

        // Get the user by username
        List<UserRepresentation> userList = usersResource.search(userName);
        if (userList.isEmpty()) {
            throw new BadRequestException("User not found");
        }
        UserRepresentation kcUser = userList.get(0);

        System.out.println(userList);
        System.out.println(userList);
        System.out.println(userList);
        System.out.println(userList);

        // Get the list of available roles in the realm
        List<RoleRepresentation> availableRoles = realmResource.roles().list();

        // Find the role with the specified name
        RoleRepresentation targetRole = null;
        for (RoleRepresentation role : availableRoles) {
            if (role.getName().equals(keycloakUser.getRole())) {
                targetRole = role;
                break;
            }
        }

        if (targetRole == null) {
            throw new BadRequestException("Role not found");
        }

        System.out.println(targetRole);
        System.out.println(targetRole);
        System.out.println(targetRole);
        System.out.println(targetRole);

        // Assign the role to the user
        List<String> realmRoles = kcUser.getRealmRoles();
        if (realmRoles == null) {
             realmRoles = new ArrayList<>();
        }
        if (!realmRoles.contains(targetRole.getName())) {
            realmRoles.add(targetRole.getName());
        }
        kcUser.setRealmRoles(realmRoles);

        // Update the user in Keycloak
        UserResource userResource = usersResource.get(kcUser.getId());
        if (userResource == null) {
            throw new BadRequestException("User not found");
        }
        // Assign the role to the user
        realmRoles = kcUser.getRealmRoles();
        if (realmRoles == null) {
            realmRoles = new ArrayList<>();
        }
        if (!realmRoles.contains(targetRole.getName())) {
            realmRoles.add(targetRole.getName());
        }
        kcUser.setRealmRoles(realmRoles);

// Update the user in Keycloak
        userResource = usersResource.get(kcUser.getId());
        if (userResource == null) {
            throw new BadRequestException("User not found");
        }
        userResource.update(kcUser);

// Verify that the role was added to the user
        List<String> updatedRealmRoles = userResource.toRepresentation().getRealmRoles();
        if (updatedRealmRoles == null || !updatedRealmRoles.contains(targetRole.getName())) {
            logger.error("Failed to assign role {} to user {}", targetRole.getName(), userName);
            throw new RuntimeException("Failed to assign role to user");
        }

// Log the updated user and role
        logger.info("Assigned role {} to user {}", targetRole.getName(), userName);

    }

*/

        public void sendVerificationLink(String userId){
        UsersResource usersResource = getInstance();
        usersResource.get(userId)
                .sendVerifyEmail();
    }
    public void sendResetPassword(String userId){
        UsersResource usersResource = getInstance();

        usersResource.get(userId)
                .executeActionsEmail(Arrays.asList("UPDATE_PASSWORD"));
    }




}
