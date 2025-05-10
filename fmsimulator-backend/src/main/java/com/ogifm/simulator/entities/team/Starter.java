package com.ogifm.simulator.entities.team;

import com.ogifm.simulator.entities.player.Player;
import com.ogifm.simulator.enums.Role;
import com.ogifm.simulator.enums.TacticalFormation;

public class Starter {

    private Player player;
    private int currentRating;
    private boolean outOfPosition;

    public Starter() {
        this.player = null;
        this.currentRating = 0;
        this.outOfPosition = false;
    }

    public boolean isEmpty() {
        return this.player == null;
    }

    public void setPlayer(Player player) {
        this.player = player;
        if (this.player != null) {
            this.currentRating = this.player.getRating();
            if (this.getOutOfPosition()) {
                if (this.player.getContract().getRoleDTO().getStringValue().equals(Role.GK.getStringValue()) || this.player.getPreferredPositions().getPrimaryPosition().isGoalkeeper()) {
                    this.currentRating = this.currentRating - 49;
                }else{
                    this.currentRating = this.currentRating - 30;
                }
                
                if (this.currentRating < 1) {
                    this.currentRating = 1;
                }
            }
        } else {
            this.currentRating = 0;
            this.outOfPosition = false;
        }
    }

    public void setCurrentRating(int ratingCurrent) {
        this.currentRating = ratingCurrent;
    }

    public void setOutOfPosition(boolean outOfPosition) {
        this.outOfPosition = outOfPosition;
    }

    public Player getPlayer() {
        return this.player;
    }

    public int getCurrentRating() {
        return this.currentRating;
    }

    public boolean getOutOfPosition() {
        return this.outOfPosition;
    }

    public String getRatingColor() {
        return TacticalFormation.getRatingColor(currentRating);
    }
}

