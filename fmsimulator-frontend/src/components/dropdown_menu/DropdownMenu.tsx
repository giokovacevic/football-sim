import { useState } from "react";
import styles from './DropdownMenu.module.css';

type DropdownMenuProps<T, K extends keyof T> = {
    items: T[],
    onSelect: (item: T) => void,
    valueProperty: K,
    imageProperty: K | undefined,
    imagePath: string,
    placeholder: string;
};

function DropdownMenu<T, K extends keyof T>({items, onSelect, valueProperty, imageProperty, imagePath, placeholder}:DropdownMenuProps<T, K>) {
    const [isOpen, setIsOpen] = useState<boolean>(false);
    const [selectedItem, setSelectedItem] = useState<T | undefined>(undefined);

    const toggleDropdownMenu = ():void => {
        setIsOpen(!isOpen);
    }

    function getToggleStateActivity() : string{
        return (isOpen ? "active" : "inactive");
    }

    function handleSelection(item:T) {
        setIsOpen(!isOpen);
        onSelect(item);
        setSelectedItem(item);
    }

    return (
        <div className={styles.root}>
            <div className={styles.header} onClick={toggleDropdownMenu}>
                <div className={styles.header_text}>
                    {selectedItem ? String(selectedItem[valueProperty]) : placeholder}
                </div>
                <div className={styles.header_icon}>
                    <img src={"./assets/dropdown_arrow_" + getToggleStateActivity() + ".png"} alt={isOpen.toString()}></img>
                </div>
            </div>
            {isOpen && (
                <div className={styles.items}>
                    {items.map((item:T, index:number) => (
                        <div key={index} className={styles.item} onClick={() => handleSelection(item)}>
                            <div className={styles.item_icon}>
                                {imageProperty 
                                ?
                                <img src={(imageProperty ? imagePath : (imagePath + item[imageProperty] + ".png"))} alt={imagePath}></img> 
                                : 
                                null
                                }
                            </div>
                            <div className={styles.item_text}>{String(item[valueProperty])}</div>
                        </div>
                    ))}
                </div>
            )}
        </div>
    );
}
export default DropdownMenu;