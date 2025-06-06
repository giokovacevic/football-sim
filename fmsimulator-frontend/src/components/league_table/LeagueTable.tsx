import type { ICompetitor } from "../../types/models/competition/Competitor";
import type { ILeague } from "../../types/models/competition/League";
import type { ITeam } from "../../types/models/team/Team";
import styles from './LeagueTable.module.css';

type LeagueTableProps = {
    league: ILeague,
    onCompetitorClick: ((team: ITeam) => void) | undefined;
};

function LeagueTable({league, onCompetitorClick} : LeagueTableProps) {
    return (
        <div className={styles.root}>
            {league.competitors.map((competitor: ICompetitor, index:number) => (
                <div key={competitor.team.id} className={styles.leaguemember_root} onClick={onCompetitorClick ? () => onCompetitorClick(competitor.team) : undefined} style={{cursor: onCompetitorClick ? 'pointer' : 'arrow'}}>
                    <div className={styles.leaguemember_placement}>{index + 1}</div>
                    <div className={styles.leaguemember_logo}>
                        <img src={"/club_logo_icons/"+ competitor.team.id +".png"}></img>
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