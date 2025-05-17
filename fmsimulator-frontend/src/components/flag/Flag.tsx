import { useState } from 'react';
import type { Country } from '../../model/country/Country';
import Tooltip from '../tooltip/Tooltip';
import styles from './Flag.module.css';

const Flag = ({country}:{country:Country}) => {
    const [hovered, setHovered] = useState<boolean>(false);

    return (
        <div className={styles.flag_root}>
            <img className={styles.flag} onMouseEnter={() => setHovered(true)} onMouseLeave={() => setHovered(false)} src={"./country_flag_icons/" + country.id + ".png"} alt={country.id}></img>
            <Tooltip text={country.name} visible={hovered}></Tooltip>
        </div>
    );
}

export default Flag;