package ru.rutmiit.dto;

public class IdDTO {

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "IdDTO{" +
                "id=" + id +
                '}';
    }
}
