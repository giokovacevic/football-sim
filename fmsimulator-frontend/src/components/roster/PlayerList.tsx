import { useState } from 'react';
import type { Player } from '../../model/player/Player';
import styles from './PlayerList.module.css';
import PlayerListHeader from './PlayerListHeader';
import PlayerListMember from './PlayerListMember';

export type PlayerListVariant = 'lineup' | 'squad' | 'global';

type PlayerListProps = {
    players: Player[];
    variant: PlayerListVariant;
    includeHeader: boolean;
};

const PlayerList = ({players, variant, includeHeader}:PlayerListProps) => {
    const [sortedPlayers, setSortedPlayers] = useState<Player[]>([...players]);

    return (
        <div className={styles.playerlist_root}>
            {includeHeader && (<PlayerListHeader variant={variant}></PlayerListHeader>)}
            {sortedPlayers.map((player) => <PlayerListMember key={player.id} player={player} variant={variant}></PlayerListMember>)}
        </div>
    );
}

export default PlayerList;