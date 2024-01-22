package edu.school21.application.services;

import edu.school21.application.entities.RolesEntity;
import edu.school21.application.entities.UsersEntity;
import edu.school21.application.enums.Access;
import edu.school21.application.graphql.models.UserModel;
import edu.school21.application.repositories.UsersRepository;
import edu.school21.application.services.converters.UserConverter;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
@Service
@AllArgsConstructor
public class CustomerUserService implements UserDetailsService {

    private UsersRepository repository;
    private UserConverter converter;

    public Set<RolesEntity> getCurrentUserRoles() {
        final String login = SecurityContextHolder.getContext().getAuthentication().getName();
        return repository.findByUserNameOrEmail(login, login)
                         .orElseThrow(() -> new RuntimeException("User not found"))
                         .getRoles();
    }

    public Access getAccessType() {
        if(getCurrentUserRoles()
                .stream()
                .noneMatch(role -> "ROLE_ADMIN".equalsIgnoreCase(role.getName()))
        ) {
            return Access.PUBLIC;
        }
        return Access.PRIVATE;
    }

    public UserModel loadUserByUserAuthName(final String authName) {
        return converter.entityToDto(
                repository.findByUserNameOrEmail(authName, authName)
                          .orElseThrow(()-> new RuntimeException("User not found"))
        );
    }
    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        final UsersEntity user = repository.findByUserNameOrEmail(username, username)
                                           .orElseThrow(()-> new RuntimeException("User not found"));

        final Set<GrantedAuthority> authorities = user.getRoles()
                                                      .stream()
                                                      .map(role -> new SimpleGrantedAuthority(role.getName()))
                                                      .collect(Collectors.toSet());
        return new User(username, user.getPassword(), authorities);
    }
}
