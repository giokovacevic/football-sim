import type { IPlayer } from '../../types/models/player/Player';
import PlayerLine from '../player_line/PlayerLine';
import PlayerLineHeader from '../player_line/PlayerLineHeader';
import styles from './PlayerList.module.css';

type PlayerListProps = {
    players: IPlayer[],
    currentYear: number,
    onHeaderClicked: (sortingKey: keyof IPlayer, sortingOrientation?: string) => void;
    onLineClicked?: (player: IPlayer) => void;
};

const PlayerList = ({players, currentYear, onLineClicked, onHeaderClicked}: PlayerListProps) => {
    return (
        <div className={styles.root}>
            <PlayerLineHeader onHeaderClicked={onHeaderClicked}></PlayerLineHeader>
            {players.map(player => <PlayerLine key={player.id} player={player} currentYear={currentYear}></PlayerLine>)}
        </div>
    );
}
export default PlayerList;