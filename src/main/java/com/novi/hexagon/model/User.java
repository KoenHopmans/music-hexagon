package com.novi.hexagon.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, length = 255)
    private String password;

    @Column(nullable = false)
    private boolean enabled = true;

    @Column
    private String apikey;

    @Column
    private String email;

    @Column
    private String photo;

    @Column(name = "birthDate")
    private String birthDate;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String about;

    @Column
    private String location;

    @Column
    private String gender;



    @OneToMany(
            targetEntity = com.novi.hexagon.model.Authority.class,
            mappedBy = "username",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER)
    private Set<com.novi.hexagon.model.Authority> authorities = new HashSet<>();


    @OneToMany(
            targetEntity = com.novi.hexagon.model.Demo.class,
            mappedBy = "username",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER)
    private Set<com.novi.hexagon.model.Demo> demos = new HashSet<>();

    public String getUsername() { return username; }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getFirstName() { return firstName;}
    public void setFirstName(String firstName) { this.firstName = firstName;}
    public String getLastName() { return lastName;}
    public void setLastName(String lastName) { this.lastName = lastName;}
    public String getAbout() { return about;}
    public void setAbout(String about) { this.about = about;}
    public String getLocation() { return location;}
    public void setLocation(String location) { this.location = location;}
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public boolean isEnabled() { return enabled;}
    public void setEnabled(boolean enabled) { this.enabled = enabled; }
    public String getApikey() { return apikey; }
    public void setApikey(String apikey) { this.apikey = apikey; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email;}
    public String getGender() { return gender;}
    public void setGender(String gender) { this.gender = gender;}
    public String getPhoto() { return photo; }
    public void setPhoto(String phoneNumber) { this.photo = phoneNumber; }
    public String getBirthDate() { return birthDate; }
    public void setBirthDate(String birthDate) { this.birthDate = birthDate; }
    public Set<Authority> getAuthorities() { return authorities; }
    public void addAuthority(Authority authority) {
        this.authorities.add(authority);
    }
    public void removeAuthority(Authority authority) {
        this.authorities.remove(authority);
    }

    public Set<Demo> getDemos() { return demos; }
    public void addDemo(Demo demo) {
        this.demos.add(demo);
    }
    public void removeDemo(Demo demo) {
        this.demos.remove(demo);
    }

}