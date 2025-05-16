import { Position } from "../../model/player/Position";
import styles from './Position.module.css';


function Positions({positions}:{positions:Position[]}) {

    return(
        <div className={styles.root}>
            {positions.map((position:Position, index:number) => (
                <div className={styles.position} key={index}>
                    <div className={styles.position_text} style={{color: `${position.color}`}}>{position.stringValue}</div>
                </div>
            ))}
        </div>
    );
}
export default Positions;