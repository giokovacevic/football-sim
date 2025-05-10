import "./comp_style.css";

function LeagueMember(props:any) {
    return (
        <div className="leaguemember-root">
            <div className="leaguemember-placement">{props.index + 1}</div>
            <div className="leaguemember-logo">
                <img src={"club_logo_icons/"+ props.competitor.team.id +".png"}></img>
            </div>
            <div className="leaguemember-name">{props.competitor.team.name}</div>
            <div className="leaguemember-wins">{props.competitor.wins}</div>
            <div className="leaguemember-draws">{props.competitor.draws}</div>
            <div className="leaguemember-losses">{props.competitor.losses}</div>
            <div className="leaguemember-scored">{props.competitor.goalsScored}</div>
            <div className="leaguemember-conceded">{props.competitor.goalsConceded}</div>
            <div className="leaguemember-points">{props.competitor.points}</div>
        </div>
    );
}
export default LeagueMember;