import "./positions_style.css";

function Positions({positions}:any) {

    return(
        <div className="positions">
            {positions.map((position:any, index:number) => (
                <div className="position" key={index}>
                    <div className="position-text" style={{color: `${position.color}`}}>{position.stringValue}</div>
                </div>
            ))}
        </div>
    );
}
export default Positions;