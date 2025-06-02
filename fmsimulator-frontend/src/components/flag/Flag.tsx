import { useState } from 'react';
import type { ICountry } from '../../types/models/country/Country';
import Tooltip from '../tooltip/Tooltip';
import styles from './Flag.module.css';

const Flag = ({country}:{country: ICountry}) => {
    const [hovered, setHovered] = useState<boolean>(false);

    return (
        <div className={styles.flag_root}>
            <div className={styles.flag_wrapper} onMouseEnter={() => setHovered(true)} onMouseLeave={() => setHovered(false)}>
                <img src={`./country_flag_icons/${country.id}.png`}></img>
                <Tooltip text={country.name} visible={hovered}></Tooltip>
            </div>
        </div>
    );
}

export default Flag;