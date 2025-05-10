import "./comp_style.css";
import LeagueMember from "./LeagueMember";

function LeagueTable(props:any) {
    return (
        <div className="leaguetable-root">
            {props.competitors.map((item:any, index:any) => (
                <LeagueMember key={index} index={index} competitor={item}></LeagueMember>
            ))}
        </div>
    );
}
export default LeagueTable;