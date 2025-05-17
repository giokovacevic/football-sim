import { useState } from "react";
import Tooltip from "../tooltip/Tooltip";
import styles from './Positions.module.css';
import Position  from './Position';
import type { Position as IPosition} from "../../model/player/Position";

function Positions({positions}:{positions:IPosition[]}) {

    return(
        <div className={styles.positions_root}>
            {positions.map((position:IPosition, index:number) => (
                <Position key={index} position={position}></Position>
            ))}
        </div>
    );
}
export default Positions;