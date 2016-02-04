package net.organizer.dto;

import java.util.Base64;

/**
 * Created by Nikolay on 27.01.2016.
 */
public class AuthUser {
    private Integer id;
    private String name;
    private String role;
    private byte[] photo;
    private String imgSrc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getImgSrc() {
        String imgBase64 = Base64.getEncoder().encodeToString(photo);
        imgSrc = String.format("data:image/jpg;base64,{0}", imgBase64);
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    @Override
    public String toString() {
        return "AuthUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
