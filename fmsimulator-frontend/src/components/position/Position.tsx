import { useState } from 'react';
import type { IPosition } from '../../types/models/position/Position';
import styles from './Positions.module.css';
import Tooltip from '../tooltip/Tooltip';
import { getPositionColor } from '../../utils/ColorUtils';

type PositionProps = {
    position: IPosition;
};

const Position = ({position}:PositionProps) => {
    const [hovered, setHovered] = useState<boolean>(false);
    
    return (
        <div className={styles.position_root}>
            <div className={styles.position_content} style={{color: `${getPositionColor(position)}`}} onMouseEnter={() => setHovered(true)} onMouseLeave={() => setHovered(false)}>{position.stringValue}
                <Tooltip text={position.name} visible={hovered}></Tooltip>
            </div>
            
        </div>
    );
}
export default Position;