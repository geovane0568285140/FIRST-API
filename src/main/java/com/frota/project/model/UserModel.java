package com.frota.project.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "[user]")
public class UserModel implements UserDetails {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id_user;

    @Column(name = "full_name", nullable = false, length = 100)
    private String fullName;

    @Column(name = "name_user", nullable = false, length = 100)
    private String nameUser;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "password_hash", nullable = false)
    private String password_hash;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_user", nullable = false, length = 20)
    private UserRole type_user;

    @Column(name = "active", nullable = false)
    private Boolean active;

    @Column(name = "create_at", nullable = true, insertable = false, updatable = false)
    private LocalDateTime create_at;

    @Column(name = "cpf", length = 14)
    private String cpf;

    @Column(name = "date_birth")
    private LocalDateTime date_brith;

    @Column(name = "num_cnh", length = 11)
    private String num_cnh;

    @Column(name = "category_cnh", length = 5)
    private String category_cnh;

    @Column(name = "date_emission_cnh")
    private LocalDateTime date_emission_cnh;

    @Column(name = "date_validity_cnh")
    private LocalDateTime date_validity_cnh;

    @Column(name = "registration_renach_cnh", length = 20)
    private String registration_renach_cnh;

    public UserModel() {
        // construtor vazio exigido pelo JPA
    }

    public UserModel(String _fullName, String _name_user, String _password_hash, String _email, UserRole _type_user, Boolean _active, String _cpf, LocalDateTime _date_brith, String _num_cnh, String _category_cnh, LocalDateTime _date_emission_cnh, LocalDateTime _date_validity_cnh, String _registration_renach_cnh) {
        this.email = _email;
        this.password_hash = _password_hash;
        this.fullName = _fullName;
        this.nameUser = _name_user;
        this.type_user = _type_user;
        this.active = _active;
        this.cpf = _cpf;
        this.date_brith = _date_brith;
        this.num_cnh = _num_cnh;
        this.category_cnh = _category_cnh;
        this.date_emission_cnh = _date_emission_cnh;
        this.date_validity_cnh = _date_validity_cnh;
        this.registration_renach_cnh = _registration_renach_cnh;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.type_user == UserRole.ADMIN) {
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_MODERATOR"), new SimpleGrantedAuthority("USER"));
        } else if (this.type_user == UserRole.MODERATOR) {
            return List.of(new SimpleGrantedAuthority("ROLE_MODERATOR"), new SimpleGrantedAuthority("ROLE_USER"));
        } else {
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }
    }

    @Override
    public String getPassword() {
        return password_hash;
    }

    @Override
    public String getUsername() {
        return id_user.toString();
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

    public UUID getId_user() {
        return id_user;
    }

    public void setId_user(UUID id_user) {
        this.id_user = id_user;
    }

    public String getName() {
        return fullName;
    }

    public void setName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword_hash() {
        return password_hash;
    }

    public void setPassword_hash(String password_hash) {
        this.password_hash = password_hash;
    }

    public UserRole getType_user() {
        return type_user;
    }

    public void setType_user(UserRole type_user) {
        this.type_user = type_user;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDateTime getCreate_at() {
        return create_at;
    }

    public void setCreate_at(LocalDateTime create_at) {
        this.create_at = create_at;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }


    public LocalDateTime getDate_brith() {
        return date_brith;
    }

    public void setDate_brith(LocalDateTime date_brith) {
        this.date_brith = date_brith;
    }

    public String getNum_cnh() {
        return num_cnh;
    }

    public void setNum_cnh(String num_cnh) {
        this.num_cnh = num_cnh;
    }

    public String getCategory_cnh() {
        return category_cnh;
    }

    public void setCategory_cnh(String category_cnh) {
        this.category_cnh = category_cnh;
    }

    public LocalDateTime getDate_emission_cnh() {
        return date_emission_cnh;
    }

    public void setDate_emission_cnh(LocalDateTime date_emission_cnh) {
        this.date_emission_cnh = date_emission_cnh;
    }

    public LocalDateTime getDate_validity_cnh() {
        return date_validity_cnh;
    }

    public void setDate_validity_cnh(LocalDateTime date_validity_cnh) {
        this.date_validity_cnh = date_validity_cnh;
    }

    public String getRegistration_renach_cnh() {
        return registration_renach_cnh;
    }

    public void setRegistration_renach_cnh(String registration_renach_cnh) {
        this.registration_renach_cnh = registration_renach_cnh;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }
}
