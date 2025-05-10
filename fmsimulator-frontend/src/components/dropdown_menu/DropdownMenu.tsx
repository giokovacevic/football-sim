import { useState } from "react";
import "./dropdown_menu_style.css";

function DropdownMenu({items, selectedItem, onSelect, textKey, iconKey, iconPath}:any) {
    const [isOpen, setIsOpen] = useState<boolean>(false);

    const toggleDropdownHandler = () => {
        setIsOpen(!isOpen);
    }

    function checkToggleState() : string{
        return (isOpen ? "active" : "inactive");
    }

    function selectionHandler(item:any) {
        onSelect(item);
        setIsOpen(!isOpen);
    }

    return (
        <div className="dropdown-menu">
            <div className="header" onClick={toggleDropdownHandler}>
                <div className="header-text">
                    {selectedItem ? selectedItem[textKey] : "Select"}
                </div>
                <div className="header-icon">
                    <img src={"./assets/dropdown_arrow_" + checkToggleState() + ".png"} alt={isOpen.toString()}></img>
                </div>
            </div>
            {isOpen && (
                <div className="items">
                    {items.map((item:any, index:number) => (
                        <div key={index} className="item" onClick={() => selectionHandler(item)}>
                            <div className="item-icon">
                                <img src={((iconKey === "_default") ? iconPath : (iconPath + item[iconKey] + ".png"))} alt={iconPath}></img>
                            </div>
                            <div className="item-text">{item[textKey]}</div>
                        </div>
                    ))}
                </div>
            )}
        </div>
    );
}
export default DropdownMenu;