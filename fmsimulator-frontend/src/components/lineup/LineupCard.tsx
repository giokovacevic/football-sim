import { useState } from "react";

function LineupCard() {
    const [item, setItem] = useState<any>(null);

    function dragOnDropHandler(e:React.DragEvent) {
        e.preventDefault();
        const data = e.dataTransfer.getData("application/json");
        const f = JSON.parse(data);
        setItem(f);
        console.log("Country name:  ", f);
    }
    function dragOnOverHandler(e: React.DragEvent) {
        e.preventDefault();
    }
    function toggleDropdownHandler(e:any) {
        e.preventDefault();
        setItem(null);
    }

    return (
        <div style={{marginLeft: '100px', marginTop: '50px', height: '60px', backgroundColor: 'red', width: '200px'}} onDrop={dragOnDropHandler} onDragOver={dragOnOverHandler} onContextMenu={(e) => toggleDropdownHandler(e)}>{item ? item.id : "Empty"}</div>
    );
}
export default LineupCard;