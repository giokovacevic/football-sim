import type { Player } from '../../types/models/player/Player';
import PlayerLine from '../player_line/PlayerLine';
import PlayerLineHeader from '../player_line/PlayerLineHeader';
import styles from './PlayerList.module.css';

type PlayerListProps = {
    players: Player[],
    currentYear: number,
};

const PlayerList = ({players, currentYear}: PlayerListProps) => {
    return (
        <div className={styles.root}>
            <PlayerLineHeader onHeaderClicked={() => {console.log("LALA")}}></PlayerLineHeader>
            {players.map(player => <PlayerLine key={player.id} player={player} currentYear={currentYear}></PlayerLine>)}
        </div>
    );
}
export default PlayerList;