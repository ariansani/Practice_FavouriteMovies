package com.example.April12Revision.models;

public class Actor {

    private Integer actorId;
    private Integer actorName;
    private boolean isDeleted;

    public Integer getActorId() {
        return actorId;
    }

    public void setActorId(Integer actorId) {
        this.actorId = actorId;
    }

    public Integer getActorName() {
        return actorName;
    }

    public void setActorName(Integer actorName) {
        this.actorName = actorName;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

}
