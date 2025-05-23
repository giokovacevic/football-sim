import type { Competitor } from "../../types/competition/Competitor";
import type { League } from "../../types/competition/League";
import type { Team } from "../../types/team/Team";
import styles from './LeagueTable.module.css';

type LeagueTableProps = {
    league: League,
    onCompetitorClick: ((team:Team) => void) | undefined;
};

function LeagueTable({league, onCompetitorClick} : LeagueTableProps) {
    return (
        <div className={styles.root}>
            {league.competitors.map((competitor:Competitor, index:number) => (
                <div key={competitor.team.id} className={styles.leaguemember_root} onClick={onCompetitorClick ? () => onCompetitorClick(competitor.team) : undefined} style={{cursor: onCompetitorClick ? 'pointer' : 'arrow'}}>
                    <div className={styles.leaguemember_placement}>{index + 1}</div>
                    <div className={styles.leaguemember_logo}>
                        <img src={"club_logo_icons/"+ competitor.team.id +".png"}></img>
                    </div>
                    <div className={styles.leaguemember_name}>{competitor.team.name}</div>
                    <div className={styles.leaguemember_wins}>{competitor.wins}</div>
                    <div className={styles.leaguemember_draws}>{competitor.draws}</div>
                    <div className={styles.leaguemember_losses}>{competitor.losses}</div>
                    <div className={styles.leaguemember_scored}>{competitor.goalsScored}</div>
                    <div className={styles.leaguemember_conceded}>{competitor.goalsConceded}</div>
                    <div className={styles.leaguemember_points}>{competitor.points}</div>
                </div>
            ))}
        </div>
    );
}
export default LeagueTable;