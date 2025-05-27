import type { Position } from "../types/models/position/Position";

export const getRatingColor = (rating:number) =>  {
    if(rating >= 95) {
            return "#00e0f5";
        }else if(rating >= 90) {
            return "#05ffe6";
        }else if(rating >= 85) {
            return "#00e63d";
        }else if(rating >= 80) {
            return "#9df700";
        }else if(rating >= 75) {
            return "#ffde3b";
        }else if(rating >= 70) {
            return "#d9c17c";
        }else if(rating >= 65) {
            return "#ffa10a";
        }else if(rating >= 60) {
            return "#f25405";
        }else{
            return "#ff0000";
        }  
}

export const getPositionColor = (position:Position) =>  {
    if (position.type === "mf") {
        return "#fa332f";
    } else if (position.type === "df") {
        return "#02d65a";
    } else if (position.type === "fw") {
        return "#409cff";
    } else if (position.type === "gk") {
        return "#ffbe19";
    } else {
        return "#4f4f4f";
    }
}
export const getContractColor = (expireDate:number, currentYear:number):string => {
    let diff = expireDate - currentYear;
    switch (diff) {
        case 0:
            return "transparent";
        case 1:
            return "#ff8d58";
        case 2:
            return "#fff56e";    
        default:
            return "#4aff81";
    }
}
export const getStaminaColor = (stamina:number) => {
    if(stamina > 80) {
        return "#00e331";
    }else if(stamina > 65){
        return "#93ff26";
    }else if(stamina > 45) {
        return "#f5ff3b";
    }else if(stamina > 25) {
        return "#f29500";
    }
    return "#ff2d03";
}