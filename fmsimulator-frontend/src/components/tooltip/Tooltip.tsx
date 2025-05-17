import styles from './Tooltip.module.css';

type TooltipProps = {
    text: string,
    visible: boolean;
}

const Tooltip = ({text, visible}:TooltipProps) => {

    return (
        <div style={{visibility: visible ? 'visible': 'hidden'}} className={styles.tooltip_root}>{text}</div>
    );
}
export default Tooltip;