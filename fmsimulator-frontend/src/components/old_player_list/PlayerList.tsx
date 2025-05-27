import { useEffect, useRef, useState } from 'react';
import type { Player } from '../../types/models/player/Player';
import styles from './PlayerList.module.css';
import PlayerListHeader from './PlayerListHeader';
import PlayerListMember from './PlayerListMember';
import { comparePlayer } from '../../utils/PlayerUtils';

export type PlayerListVariant = 'lineup' | 'squad' | 'preview';

type PlayerListProps = {
    players: Player[];
    currentYear: number;
    variant: PlayerListVariant;
    includeHeader: boolean;
};

type SortingOrder = 'desc' | 'asc';

const PlayerList = ({players, currentYear, variant, includeHeader}:PlayerListProps) => {
    const [sortedPlayers, setSortedPlayers] = useState<Player[]>([...players]);
    const lastSortingKey = useRef<keyof Player>('contract');
    const lastSortingOrder = useRef<SortingOrder>('desc');

    useEffect(() => {
        setSortedPlayers([...players])
    }, [players]);

    const handleHeaderOnClick = (sortingKey:(keyof Player), orientation?:string) => {
        if(lastSortingKey.current === sortingKey && sortingKey !== 'positions') {
            if(lastSortingOrder.current === 'desc') {
                sortPlayers(sortingKey, 'asc', orientation);
            }else{
                sortPlayers(sortingKey, 'desc', orientation);
            }
        }else{
            sortPlayers(sortingKey, 'asc', orientation);
        }
    }

    const sortPlayers = (sortingKey:(keyof Player), sortingOrder:SortingOrder, orientation?:string) => {
        const sorted = [...players].sort((a, b) => {
            const result = comparePlayer(a, b, sortingKey, orientation);
            return sortingOrder === 'asc' ? result : -result;
        });
        setSortedPlayers(sorted);
        lastSortingKey.current = sortingKey;
        lastSortingOrder.current = sortingOrder;
    }

    return (
        <div className={styles.playerlist_root}>
            {includeHeader && (<PlayerListHeader onHeaderClicked={handleHeaderOnClick} variant={variant}></PlayerListHeader>)}
            {sortedPlayers.map((player) => <PlayerListMember key={player.id} player={player} currentYear={currentYear} variant={variant}></PlayerListMember>)}
        </div>
    );
}

export default PlayerList;