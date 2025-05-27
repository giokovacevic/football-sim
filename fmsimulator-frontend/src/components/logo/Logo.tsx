import { useState } from 'react';
import Tooltip from '../tooltip/Tooltip';
import styles from './Logo.module.css';

type LogoProps = {
    text: string,
    url: string;
};

const Logo = ({text, url}: LogoProps) => {
    const [hovered, setHovered] = useState<boolean>(false);
    
    return (
        <div className={styles.root}>
            <div className={styles.wrapper} onMouseEnter={() => setHovered(true)} onMouseLeave={() => setHovered(false)}>
                <img src={url} alt={text}></img>
                <Tooltip text={text} visible={hovered}></Tooltip>
            </div>
        </div>
    );
}
export default Logo;