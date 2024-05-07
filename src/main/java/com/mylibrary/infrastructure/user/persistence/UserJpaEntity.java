package com.mylibrary.infrastructure.user.persistence;

import com.mylibrary.domain.user.User;
import com.mylibrary.domain.user.UserID;
import com.mylibrary.domain.user.UserRole;
import com.mylibrary.domain.valueobjects.PersonName;
import com.mylibrary.domain.valueobjects.password.Password;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;

@Entity(name = "User")
@Table(name = "\"user\"")
public class UserJpaEntity implements UserDetails {
    @Id
    @Column(name = "id", nullable = false, length = 32)
    private String id;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "document", nullable = false, unique = true)
    private String document;

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private UserRole role;

    @Column(name = "salt", nullable = false)
    private byte[] salt;


    public UserJpaEntity() {
    }

    private UserJpaEntity(String id, String name, String document, String password, UserRole role, Instant createdAt, byte[] salt) {
        this.id = id;
        this.document = document;
        this.password = password;
        this.role = role;
        this.createdAt = createdAt;
        this.name = name;
        this.salt = salt;
    }

    public static UserJpaEntity from(final User user) {
        return new UserJpaEntity(
                user.getId().getValue(),
                user.getName().getValue(),
                user.getDocument(),
                user.getPassword().getValue(),
                user.getRole(),
                user.getCreatedAt(),
                user.getPassword().getSalt()
        );
    }

    public User toUser() {
        return User.with(
                UserID.from(getId()),
                PersonName.from(getName()),
                getDocument(),
                Password.from(getPassword(), getSalt()),
                getRole(),
                getCreatedAt()
        );
    }

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        var authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_CLIENT"));
        if (getRole() == UserRole.ATTENDANT) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ATTENDANT"));
        }
        return authorities;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUsername() {
        return getDocument();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}