import { Position } from "../../model/player/Position";
import "./positions_style.css";


function Positions({positions}:{positions:Position[]}) {

    return(
        <div className="positions">
            {positions.map((position:Position, index:number) => (
                <div className="position" key={index}>
                    <div className="position-text" style={{color: `${position.color}`}}>{position.stringValue}</div>
                </div>
            ))}
        </div>
    );
}
export default Positions;