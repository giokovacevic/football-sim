import styles from './Positions.module.css';
import Position  from './Position';
import type { Position as IPosition} from "../../types/position/Position";

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